package org.javaboy.meeting.mapper;

import org.apache.ibatis.annotations.Param;
import org.javaboy.meeting.domain.Employee;
import org.javaboy.meeting.domain.Meeting;

import java.util.Date;
import java.util.List;

public interface MeetingMapper {
    /**
     * 添加会议
     * @param meeting
     * @return
     */
    Integer addMeeting(@Param("m") Meeting meeting);

    /**
     * 添加员工到会议
     * @param meetingId
     * @param employeeIds
     * @return
     */
    Integer addMeetingOfEmployee(@Param("meetingId") Integer meetingId, @Param("employeeIds") Integer[] employeeIds);

    /**
     * 查找预定的会议(不报含已撤销)
     * @param resId
     * @return
     */
    List<Meeting> getMeetingsByResId(Integer resId);

    /**
     * 查找指定id的会议
     * @param meetingId
     * @return
     */
    Meeting getMeetingById(Integer meetingId);

    /**
     * 查找参加会议的员工
     * @param meetingId
     * @return
     */
    List<Employee> getEmployeesByMeetingId(Integer meetingId);

    /**
     * 更新会议的状态
     * @param meetingId
     * @param status 0 开启, 1 撤销
     * @param canceledTime
     * @return
     */
    Integer updateMeetingStatus(@Param("meetingId") Integer meetingId, @Param("status") Integer status,
                                @Param("reason") String reason, @Param("canceledTime") Date canceledTime);

    /**
     * 查找需要参加的会议
     * @param employeeId
     * @return
     */
    List<Meeting> getMeetingsByEmployeeId(Integer employeeId);

    /**
     * 搜索指定会议
     * @param page
     * @param resIds
     * @param roomIds
     * @param startDate
     * @param endDate
     * @param resStartDate
     * @param resEndDate
     * @param meetingName
     * @return
     */
    List<Meeting> searchMeeting(@Param("page") Integer page, @Param("resIds") Integer[] resIds,
                                @Param("roomIds") Integer[] roomIds, @Param("startDate") Date startDate,
                                @Param("endDate") Date endDate, @Param("resStartDate") Date resStartDate,
                                @Param("resEndDate") Date resEndDate, @Param("meetingName") String meetingName);

    /**
     * 获取搜索会议的总记录数
     * @param resIds
     * @param roomIds
     * @param startDate
     * @param endDate
     * @param resStartDate
     * @param resEndDate
     * @param meetingName
     * @return
     */
    Integer getSearchMeetingTotal(@Param("resIds") Integer[] resIds, @Param("roomIds") Integer[] roomIds,
                                  @Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                  @Param("resStartDate") Date resStartDate, @Param("resEndDate") Date resEndDate,
                                  @Param("meetingName") String meetingName);

    /**
     * 获取 size 条最新的状态为 status 的会议
     * @param employeeId
     * @return
     */
    List<Meeting> getNewMeetingByEmployeeIdAndStatus(@Param("id") Integer employeeId, @Param("size") Integer size, @Param("status") Integer status);

}
