package org.javaboy.meeting.service;

import org.javaboy.meeting.domain.Employee;
import org.javaboy.meeting.domain.Meeting;
import org.javaboy.meeting.mapper.EmployeeMapper;
import org.javaboy.meeting.mapper.MeetingMapper;
import org.javaboy.meeting.mapper.MeetingRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MeetingService {

    @Autowired
    private MeetingMapper meetingMapper;

    /**
     * 添加会议
     * @param meeting
     * @param employeeIds
     * @return
     */
    @Transactional
    public boolean addMeeting(Meeting meeting, Integer[] employeeIds) {
        meetingMapper.addMeeting(meeting);
        meetingMapper.addMeetingOfEmployee(meeting.getMeetingId(), employeeIds);
        return true;
    }

    /**
     * 查找指定员工预定的会议
     * @param resId
     * @return
     */
    public List<Meeting> getMeetingsByResId(Integer resId) {
        return meetingMapper.getMeetingsByResId(resId);
    }

    /**
     * 查找指定会议
     * @param meetingId
     * @return
     */
    public Meeting getMeetingById(Integer meetingId) {
        return meetingMapper.getMeetingById(meetingId);
    }

    /**
     * 查找指定会议中参加的员工
     * @param meetingId
     * @return
     */
    public List<Employee> getEmployeesByMeetingId(Integer meetingId) {
        return meetingMapper.getEmployeesByMeetingId(meetingId);
    }

    /**
     * 更新会议状态
     * @param meetingId
     * @param status 0 开启, 1 撤销
     * @return
     */
    @Transactional
    public Integer updateMeetingStatus(Integer meetingId, Integer status, String reason) {
        Date cancelTime = null;
        if (status.equals(1)) {
            cancelTime = new Date();
        }
        return meetingMapper.updateMeetingStatus(meetingId, status, reason, cancelTime);
    }

    /**
     * 查找指定员工需要参加的会议
     * @param employeeId
     * @return
     */
    public List<Meeting> getMeetingsByEmployeeId(Integer employeeId) {
        return meetingMapper.getMeetingsByEmployeeId(employeeId);
    }

    /**
     * 搜索指定条件的会议
     * @param page
     * @param pageSize
     * @param meetingName
     * @param roomIds
     * @param resIds
     * @param startDate
     * @param endDate
     * @param resStartDate
     * @param resEndDate
     * @return
     */
    public List<Meeting> searchMeeting(Integer page, Integer pageSize, String meetingName, Integer[] roomIds, Integer[] resIds,
                                       Date startDate, Date endDate,
                                       Date resStartDate, Date resEndDate) {
        page = (page - 1) * pageSize;
        return meetingMapper.searchMeeting(page, resIds, roomIds, startDate, endDate,
                resStartDate, resEndDate, meetingName);
    }

    /**
     * 获取具有指定搜索条件会议的总记录数
     * @param meetingName
     * @param roomIds
     * @param resIds
     * @param startDate
     * @param endDate
     * @param resStartDate
     * @param resEndDate
     * @return
     */
    public Integer getSearchMeetingTotal(String meetingName, Integer[] roomIds, Integer[] resIds,
                                         Date startDate, Date endDate,
                                         Date resStartDate, Date resEndDate) {
        return meetingMapper.getSearchMeetingTotal(resIds, roomIds, startDate, endDate,
                resStartDate, resEndDate, meetingName);
    }

    /**
     * 获取最新的指定状态的会议
     * @param employeeId
     * @param size
     * @param status
     * @return
     */
    public List<Meeting> getNewMeetingByEmployeeIdAndStatus(Integer employeeId, Integer size, Integer status) {
        return meetingMapper.getNewMeetingByEmployeeIdAndStatus(employeeId, size, status);
    }
}
