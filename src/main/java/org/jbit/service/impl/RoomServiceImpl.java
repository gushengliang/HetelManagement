package org.jbit.service.impl;

import org.jbit.dao.RoomDao;
import org.jbit.dao.impl.RoomDaoMysqlImpl;
import org.jbit.dto.RoomDto;
import org.jbit.dto.RoomQueryDto;
import org.jbit.entity.Facility;
import org.jbit.entity.Room;
import org.jbit.entity.Staff;
import org.jbit.service.RoomService;
import org.jbit.utils.PageUtil;

import java.util.List;

/**
 * @author 李宇杰
 * @description
 * @date 2020/11/23 0023
 */
public class RoomServiceImpl implements RoomService {
    /**
     * 注入Dao
     */
    private RoomDao roomDao = new RoomDaoMysqlImpl();

    @Override
    public PageUtil pageList(RoomQueryDto roomQueryDto) throws Exception {
        /*
         * 统计总记录数
         * 如果总记录数>0：
         *  （1）查询列表数据
         *  （2）封装分页数据
         */
        PageUtil pageUtil = null;
        //统计总记录数
        int total = roomDao.countBySelective(roomQueryDto);
        if(total > 0){
            List<RoomDto> data = roomDao.selectBySelective(roomQueryDto);
            pageUtil = new PageUtil<>(
                    roomQueryDto.getCurrentPage(),
                    roomQueryDto.getPageSize(),
                    total,
                    data
            );
        }
        return pageUtil;
    }

    @Override
    public Room selectById(String roomId) throws Exception {
        return roomDao.selectById(roomId);
    }

    @Override
    public List<Facility> getFacilities(String roomId) throws Exception {
        return roomDao.getFacilities(roomId);
    }

    @Override
    public List<Facility> editFacilities(String roomId, List<Facility> facilities) throws Exception {
        roomDao.deleteFacilities(roomId);
        for(Facility facility: facilities) {
            roomDao.addFacilities(roomId, facility);
        }
        return roomDao.getFacilities(roomId);
    }

    @Override
    public int deleteFacilities(String roomId) throws Exception {
        return roomDao.deleteFacilities(roomId);
    }

    @Override
    public int addFacilities(String roomId, Facility facility) throws Exception {
        return roomDao.addFacilities(roomId, facility);
    }

    @Override
    public int save(Room room) throws Exception {
        return roomDao.update(room);
    }

    @Override
    public int occupyById(String id) throws Exception {
        return roomDao.occupyById(id);
    }

    @Override
    public int create(Room room) throws Exception {
        return roomDao.insert(room);
    }

    @Override
    public int handleState(Room room) throws Exception {
        return roomDao.handleState(room);
    }

    @Override
    public int isExist(Room room) throws Exception {
        return roomDao.countBySelective(room);
    }

}
