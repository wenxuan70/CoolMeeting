package org.javaboy.meeting.controller;

import org.javaboy.meeting.domain.Department;
import org.javaboy.meeting.domain.Employee;
import org.javaboy.meeting.domain.Meeting;
import org.javaboy.meeting.domain.MeetingRoom;
import org.javaboy.meeting.service.DepartmentService;
import org.javaboy.meeting.service.EmployeeService;
import org.javaboy.meeting.service.MeetingRoomService;
import org.javaboy.meeting.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MeetingController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MeetingRoomService meetingRoomService;

    @Autowired
    private MeetingService meetingService;

    private static final Integer CANCEL_MEETING = 1;

    private static final Integer OPEN_MEETING = 0;

    private static final Integer PAGE_SIZE = 10;

    private static final ThreadLocal<SimpleDateFormat> sdf =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    /* 页面接口 */

    // 我的会议页面
    @RequestMapping("/mymeetings")
    public String myMeeting(HttpSession session, Model model) {
        Employee currentUser = (Employee) session.getAttribute("currentuser");
        List<Meeting> meetings = meetingService.getMeetingsByEmployeeId(currentUser.getEmployeeId());
        model.addAttribute("meetings", meetings);
        return "mymeetings";
    }

    // 最新通知页面
    @RequestMapping("/notifications")
    public String notifications(HttpSession session, Model model) {
        Employee user = (Employee) session.getAttribute("currentuser");
        Integer id = user.getEmployeeId();
        List<Meeting> todoMeeting = meetingService.getNewMeetingByEmployeeIdAndStatus(id, PAGE_SIZE, OPEN_MEETING);
        List<Meeting> canceledMeeting = meetingService.getNewMeetingByEmployeeIdAndStatus(id, PAGE_SIZE, CANCEL_MEETING);
        model.addAttribute("todoMeeting", todoMeeting);
        model.addAttribute("canceledMeeting", canceledMeeting);
        return "notifications";
    }

    // 我的会议详情页
    @RequestMapping("/meetingdetails")
    public String meetingDetails(Integer meetingId, Model model) {
        Meeting meeting = meetingService.getMeetingById(meetingId);
        List<Employee> employees = meetingService.getEmployeesByMeetingId(meetingId);
        model.addAttribute("employees", employees);
        model.addAttribute("meeting", meeting);
        return "meetingdetails";
    }

    // 我的预定页面
    @RequestMapping("/mybookings")
    public String myBookings(HttpSession session, Model model) {
        Employee currentUser = (Employee) session.getAttribute("currentuser");
        List<Meeting> meetings = meetingService.getMeetingsByResId(currentUser.getEmployeeId());
        model.addAttribute("meetings", meetings);
        return "mybookings";
    }

    // 预定会议详情页面
    @RequestMapping("/mymeetingdetails")
    public String myMeetingDetails(Integer meetingId, Model model) {
        Meeting meeting = meetingService.getMeetingById(meetingId);
        List<Employee> employees = meetingService.getEmployeesByMeetingId(meetingId);
        model.addAttribute("meeting", meeting);
        model.addAttribute("employees", employees);
        return "mymeetingdetails";
    }

    // 搜索会议页面
    @RequestMapping("/searchmeetings")
    public String searchMeetings(@RequestParam(defaultValue = "1")Integer page, String meetingName,
                                 String roomName, String resName, Date startDate, Date endDate,
                                 Date resStartDate, Date resEndDate, Model model) {
        Integer[] resIds = null, roomIds = null;
        if (resName != null && resName.length() > 0) {
            resIds = employeeService.getEmployeeIdsByName(resName);
        }
        if (roomName != null && roomName.length() > 0) {
            roomIds = meetingRoomService.getMrIdsByName(roomName);
        }
        // 保证 page >= 1。
        if (page.compareTo(1) < 0) page = 1;
        // 获取总记录数
        Integer total = meetingService.getSearchMeetingTotal(meetingName, roomIds, resIds,
                startDate, endDate, resStartDate, resEndDate);
        // 总页数
        int pageNum = total % PAGE_SIZE == 0 ? total / PAGE_SIZE : total / PAGE_SIZE + 1;
        // 保证 page 不超出总页数，如果总页数为 0，则设置 page = 1。
        if (page.compareTo(pageNum) > 0) page = pageNum > 0 ? pageNum : 1;
        // 如果总记录数 total = 0，则不需要查询，直接令 meeting = null 即可。
        List<Meeting> meetings = total == 0 ? null : meetingService.searchMeeting(page, PAGE_SIZE, meetingName,
                roomIds, resIds, startDate, endDate, resStartDate, resEndDate);
        model.addAttribute("meetings", meetings);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("pageNum", pageNum);
        // 设置上一页。
        model.addAttribute("prev", page.equals(1) ? 1 : page - 1);
        // 设置下一页。
        model.addAttribute("next", page.equals(pageNum) ? pageNum : (page.compareTo(pageNum) > 0 ? 1 : page + 1));
        // 封装查找条件，方便上一页和下一页等查询。
        model.addAttribute("urlQuery", getUrlQuery(meetingName, roomName, resName, startDate,
                endDate, resStartDate, resEndDate));
        return "searchmeetings";
    }

    /**
     * 把请求参数转换为 url 参数
     * @param meetingName
     * @param roomName
     * @param resName
     * @param startDate
     * @param endDate
     * @param resStartDate
     * @param resEndDate
     * @return
     */
    private String getUrlQuery(String meetingName, String roomName, String resName, Date startDate,
                               Date endDate, Date resStartDate, Date resEndDate) {
        StringBuilder urlQuery = new StringBuilder();
        final char separator = '&';
        if (meetingName != null && meetingName.length() > 0) {
            urlQuery.append("meetingName=").append(urlEncode(meetingName)).append(separator);
        }
        if (roomName != null && roomName.length() > 0) {
            urlQuery.append("rooName=").append(urlEncode(roomName)).append(separator);
        }
        if (resName != null && resName.length() > 0) {
            urlQuery.append("resName=").append(urlEncode(resName)).append(separator);
        }
        if (startDate != null) {
            urlQuery.append("startDate=").append(getDateOfString(startDate)).append(separator);
        }
        if (endDate != null) {
            urlQuery.append("endDate=").append(getDateOfString(endDate)).append(separator);
        }
        if (resStartDate != null) {
            urlQuery.append("resStartDate=").append(resStartDate).append(separator);
        }
        if (resEndDate != null) {
            urlQuery.append("resEndDate").append(resEndDate).append(separator);
        }
        int len = urlQuery.length();
        if (len > 0) {
            return urlQuery.deleteCharAt(len - 1).toString();
        }
        return null;
    }

    /**
     * 把 Date 转化为指定格式并进行 url 编码的字符串。
     * @param date
     * @return
     */
    private String getDateOfString(Date date) {
        return urlEncode(sdf.get().format(date));
    }

    /**
     * 把字符串进行 url 编码
     * @param s
     * @return
     */
    private String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    // 撤销会议页面
    @RequestMapping("/cancelmeeting")
    public String ancelMeeting(Integer meetingId, Model model) {
        Meeting meeting = meetingService.getMeetingById(meetingId);
        model.addAttribute("meeting", meeting);
        return "cancelmeeting";
    }

     /* Restful 风格接口 */

    @RequestMapping("/getAllDeps")
    @ResponseBody
    public List<Department> getAllDeps() {
        return departmentService.getAllDeps();
    }

    @RequestMapping("/getEmployeesByDepId")
    @ResponseBody
    public List<Employee> getEmployeesByDepId(Integer depId) {
        return employeeService.getEmployeesByDepId(depId);
    }

    @RequestMapping("/getMeetingRooms")
    @ResponseBody
    public List<MeetingRoom> getMeetingRooms() {
        return meetingRoomService.getAllMrs();
    }

    /* 功能接口 */

    // 添加会议功能
    @PostMapping("/toBookMeeting")
    public String toBookMeeting(Meeting meeting, Integer[] employeeIds, HttpSession session) {
        Employee currentUser = (Employee) session.getAttribute("currentuser");
        meeting.setReservationistTime(new Date());
        meeting.setReservationistId(currentUser.getEmployeeId());
        meeting.setStatus(OPEN_MEETING);
        boolean result = meetingService.addMeeting(meeting, employeeIds);
        return "redirect:/mybookings";
    }

    // 撤销会议功能
    @PostMapping("/doCancelMeeting")
    public String doCancelMeeting(Integer meetingId, String cancelReason, Model model) {
        Integer result = meetingService.updateMeetingStatus(meetingId, CANCEL_MEETING, cancelReason);
        if (result.equals(1)) {
            return "redirect:/notifications";
        } else {
            model.addAttribute("meetingId", meetingId);
            return "forward:/cancelmeeting";
        }
    }
}
