package org.javaboy.meeting.service;

import org.javaboy.meeting.domain.MeetingRoom;
import org.javaboy.meeting.mapper.MeetingRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MeetingRoomService {

    @Autowired
    private MeetingRoomMapper meetingRoomMapper;

    public List<MeetingRoom> getAllMrs() {
        return meetingRoomMapper.getAllMrs();
    }

    public MeetingRoom getMeetingRoomById(Integer roomId) {
        return meetingRoomMapper.getMeetingRoomById(roomId);
    }

    @Transactional
    public Integer updateMeetingRoom(MeetingRoom meetingRoom) {
        return meetingRoomMapper.updateMeetingRoom(meetingRoom);
    }

    @Transactional
    public Integer addMeetingRoom(MeetingRoom meetingRoom) {
        return meetingRoomMapper.addMeetingRoom(meetingRoom);
    }

    /**
     * （模糊查询）通过会议室名称的会议室id
     * @param roomName
     * @return
     */
    public Integer[] getMrIdsByName(String roomName) {
        return meetingRoomMapper.getMrIdsByName(roomName);
    }
}
