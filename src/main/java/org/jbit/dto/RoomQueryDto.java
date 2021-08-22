package org.jbit.dto;

import org.jbit.entity.Room;
import org.jbit.utils.StringUtil;

import java.io.Serializable;

/**
 * @author 李宇杰
 * @description
 * @date 2020/11/23 0023
 */
public class RoomQueryDto extends Room implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 页面大小
     */
    private Integer pageSize;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public RoomQueryDto() {

    }

    public RoomQueryDto(String roomID, String roomName, String roomType, String roomPrice) {
        super(roomID, roomName, roomType, roomPrice);
    }
    public RoomQueryDto(String roomID, String roomName, String roomType, String roomPrice, Integer occupy) {
        super(roomID, roomName, roomType, roomPrice, occupy);
    }

    public RoomQueryDto(String roomID, String roomName, String roomType, String roomPrice, Integer currentPage, Integer pageSize) {
        super(roomID, roomName, roomType, roomPrice);
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public RoomQueryDto(String roomID, String roomName, String roomType, String roomPrice, Integer occupy, Integer currentPage, Integer pageSize) {
        super(roomID, roomName, roomType, roomPrice, occupy);
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
