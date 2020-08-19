package org.javaboy.meeting.mapper;

import org.apache.ibatis.annotations.Param;
import org.javaboy.meeting.domain.MeetingRoom;

import java.util.List;

public interface MeetingRoomMapper {
    List<MeetingRoom> getAllMrs();

    MeetingRoom getMeetingRoomById(Integer roomId);

    Integer updateMeetingRoom(@Param("mr") MeetingRoom meetingRoom);

    Integer addMeetingRoom(@Param("mr") MeetingRoom meetingRoom);

    Integer[] getMrIdsByName(String roomName);
}
