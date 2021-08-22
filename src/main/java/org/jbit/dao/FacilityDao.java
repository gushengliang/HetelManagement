package org.jbit.dao;

import org.jbit.dto.FacilityQueryDto;
import org.jbit.entity.Facility;

import java.util.List;

/**
 * 设施Dao
 * @author yh
 */
public interface FacilityDao extends BaseDao<Facility> {
    /**
     * 分页查询
     * @param facilityQueryDto
     * @return List<Facility>
     * @throws Exception
     */
    List<Facility> selectBySelective(FacilityQueryDto facilityQueryDto) throws Exception;

    /**
     * 查询符合条件的数据条数
     * @param facility
     * @return count
     * @throws Exception
     */
    int countBySelective(Facility facility) throws Exception;

    /**
     * 查询符合条件的数据条数
     * @param facilityQueryDto
     * @return count
     * @throws Exception
     */
    int countBySelective(FacilityQueryDto facilityQueryDto) throws Exception;

    /**
     * 根据房间号查设备列表
     * @param roomId
     * @return
     */
    List<Facility> selectByRoomId(String roomId) throws Exception;
}
