package org.javaboy.meeting.domain;

import org.javaboy.meeting.validatedGroup.Group1;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeetingRoom {
    @NotNull(message = "{roomId.notnull}", groups = Group1.class)
    private Integer roomId;
    @NotNull(message = "{roomNum.notnull}")
    @Digits(integer = 10, fraction = 0, message = "{roomNum.digits}")
    private Integer roomNum;
    @NotNull(message = "{roomName.notnull}")
    private String roomName;
    @NotNull(message = "{capacity.notnull}")
    @Digits(integer = 10, fraction = 0, message = "{capacity.digits}")
    private Integer capacity;
    @NotNull(message = "{status.notnull}")
    @Max(value = 1, message = "${status.range}")
    @Min(value = 0, message = "${status.range}")
    private Integer status;
    private String description;

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "roomId=" + roomId +
                ", roomNum=" + roomNum +
                ", roomName='" + roomName + '\'' +
                ", capacity=" + capacity +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
