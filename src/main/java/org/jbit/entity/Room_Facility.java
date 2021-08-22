package org.jbit.entity;

import java.io.Serializable;

public class Room_Facility implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 房间号
     */
    private String roomId;
    /**
     * 设施ID
     */
    private String facilityId;

    public Room_Facility() {

    }

    public Room_Facility(String roomId, String facilityId) {
        this.roomId = roomId;
        this.facilityId = facilityId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    @Override
    public String toString() {
        return "Room_Facility{" +
                "roomId='" + roomId + '\'' +
                ", facilityId='" + facilityId + '\'' +
                '}';
    }
}
