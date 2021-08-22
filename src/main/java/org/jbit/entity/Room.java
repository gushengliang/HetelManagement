package org.jbit.entity;

import java.io.Serializable;

/**
 * @description 房间类
 * @author yh
 *
 * @version 1.0,2020-11-21
 */
public class Room implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 房间号
     */
    private String roomId;
    /**
     * 房间名称
     */
    private String roomName;
    /**
     * 房间类型
     */
    private String roomType;
    /**
     * 房间价格
     */
    private String roomPrice;
    /**
     * 房间状态
     */
    private Integer roomState;

    public Room() {

    }

    /**
     * 有参构造方法1
     * @param roomId
     * @param roomName
     * @param roomType
     * @param roomPrice
     */
    public Room(String roomId, String roomName, String roomType, String roomPrice) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }

    /**
     * 有参构造方法2
     * @param roomId
     * @param roomName
     * @param roomType
     * @param roomPrice
     * @param roomState
     */
    public Room(String roomId, String roomName, String roomType, String roomPrice, Integer roomState) {
        this.roomId = roomId;
        this.roomName=roomName;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomState = roomState;
    }


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomState() {
        return roomState;
    }

    public void setRoomState(Integer roomState) {
        this.roomState = roomState;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String  getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String  roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomPrice=" + roomPrice + '\'' +
                ", roomState='" + roomState +
                '}';
    }
}
