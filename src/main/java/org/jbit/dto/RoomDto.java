package org.jbit.dto;

import org.jbit.entity.Room;
import org.jbit.enums.OccupyEnum;
import java.io.Serializable;

/**
 * @author 李宇杰
 * @description roomDto
 * @date 2020/11/24 0024
 */
public class RoomDto extends Room implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 占用状态描述
     */
    private String occupyDescription;

    public RoomDto() {

    }

    public RoomDto(String roomId, String roomName, String roomType, String roomPrice, Integer occupy) {
        super(roomId, roomName, roomType, roomPrice, occupy);
    }

    public String getOccupyDescription() {
        return OccupyEnum.getDescription(this.getRoomState());
    }

    public void setOccupyDescription(String occupyDescription) {
        this.occupyDescription = occupyDescription;
    }
}
