package org.jbit.dao;

import org.jbit.dto.RoomDto;
import org.jbit.dto.RoomQueryDto;
import org.jbit.entity.Facility;
import org.jbit.entity.Room;
import org.jbit.entity.Room_Facility;

import java.util.List;

/**
 * 房间接口
 */
public interface RoomDao extends BaseDao<Room>{

    /**
     * 分页查询
     * @param roomQueryDto
     * @return Room[]
     * @throws Exception
     */
    //List<Room> selectBySelective(RoomQueryDto roomQueryDto) throws Exception;
    List<RoomDto> selectBySelective(RoomQueryDto roomQueryDto) throws Exception;


    /**
     * 查询符合条件的数据条数
     * @param room
     * @return Room[]
     * @throws Exception
     */
    int countBySelective(Room room) throws Exception;

    /**
     * 查询符合条件的数据条数
     * @param roomQueryDto
     * @return Room[]
     * @throws Exception
     */
    int countBySelective(RoomQueryDto roomQueryDto) throws Exception;

    /**
     * 按id占用房间
     * @param id
     * @return
     */
    int occupyById(String id) throws Exception;

    /**
     * 切换房间占用状态
     * @param room
     * @return
     * @throws Exception
     */
    int handleState(Room room) throws Exception;

    /**
     * 获取房间设施
     * @param roomId
     * @return
     * @throws Exception
     */
    List<Facility> getFacilities(String roomId) throws Exception;

    /**
     * 按房间id删除设施
     * @param roomId
     * @return
     * @throws Exception
     */
    int deleteFacilities(String roomId) throws Exception;

    /**
     * 添加房间设施
     * @param roomId
     * @param facility
     * @return
     * @throws Exception
     */
    int addFacilities(String roomId, Facility facility) throws Exception;

    /**
     * 编辑房间设施
     * @param facilities
     * @return
     * @throws Exception
     */
    List<Facility> editFacilities(List<Facility> facilities) throws Exception;
}
