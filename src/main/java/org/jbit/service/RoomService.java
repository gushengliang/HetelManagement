package org.jbit.service;

import org.jbit.dto.RoomQueryDto;
import org.jbit.entity.Facility;
import org.jbit.entity.Room;
import org.jbit.utils.PageUtil;

import java.util.List;

public interface RoomService {
    /**
     * 分页查询
     * @param roomQueryDto
     * @return 分页数据
     * @throws Exception
     */
    PageUtil pageList(RoomQueryDto roomQueryDto) throws Exception;

    /**
     * 更新房间信息
     * @param room
     * @return
     * @throws Exception
     */
    int save(Room room) throws Exception;

    /**
     * 按id占用房间
     * @param id
     * @return
     * @throws Exception
     */
    int occupyById(String id) throws Exception;

    /**
     * 创建房间
     * @param room
     * @return
     */
    int create(Room room) throws Exception;


    /**
     * 切换房间占用状态
     * @param room
     * @return
     * @throws Exception
     */
    int handleState(Room room) throws Exception;

    /**
     * 检测房间id是否存在
     * @param room
     * @return
     * @throws Exception
     */
    int isExist(Room room) throws Exception;

    /**
     * 按id查找房间
     * @param roomId
     * @return
     */
    Room selectById(String roomId) throws Exception;

    /**
     * 获取房间设施
     * @param roomId
     * @return
     * @throws Exception
     */
    List<Facility> getFacilities(String roomId) throws Exception;

    /**
     * 编辑房间设施
     * @param roomId
     * @param facilities
     * @return
     * @throws Exception
     */
    List<Facility> editFacilities(String roomId, List<Facility> facilities) throws Exception;

    /**
     * 按房间id删除设施
     * @param roomId
     * @return
     * @throws Exception
     */
    int deleteFacilities(String roomId) throws Exception;

    /**
     * 添加设施
     * @param roomId
     * @param facility
     * @return
     * @throws Exception
     */
    int addFacilities(String roomId, Facility facility) throws Exception;
}
