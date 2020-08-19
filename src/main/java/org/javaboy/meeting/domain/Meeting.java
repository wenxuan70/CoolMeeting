package org.javaboy.meeting.domain;

import java.util.Date;

public class Meeting {
    private Integer meetingId;
    private String meetingName;
    private Integer roomId;
    private String roomName;
    private Integer numberOfParticipants;
    private Date startTime;
    private Date endTime;
    private String description;
    private Integer status;
    private String canceledReason;
    // 预定员id
    private Integer reservationistId;
    // 预定时间
    private Date reservationistTime;
    // 预定员名称
    private String reservationistName;

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingId=" + meetingId +
                ", meetingName='" + meetingName + '\'' +
                ", roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", reservationistId=" + reservationistId +
                ", numberOfParticipants=" + numberOfParticipants +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", reservationistTime=" + reservationistTime +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", canceledReason='" + canceledReason + '\'' +
                ", reservationistName='" + reservationistName + '\'' +
                '}';
    }

    public String getReservationistName() {
        return reservationistName;
    }

    public void setReservationistName(String reservationistName) {
        this.reservationistName = reservationistName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getReservationistId() {
        return reservationistId;
    }

    public void setReservationistId(Integer reservationistId) {
        this.reservationistId = reservationistId;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getReservationistTime() {
        return reservationistTime;
    }

    public void setReservationistTime(Date reservationistTime) {
        this.reservationistTime = reservationistTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCanceledReason() {
        return canceledReason;
    }

    public void setCanceledReason(String canceledReason) {
        this.canceledReason = canceledReason;
    }
}
