package org.jbit.dao;

import org.jbit.dto.CompensateQueryDto;
import org.jbit.entity.Compensate;

import java.util.List;

/**
 * 设施Dao
 * @author ysw
 */
public interface CompensateDao extends BaseDao< Compensate> {
    /**
     * 分页查询
     * @param compensateQueryDto
     * @return List<Facility>
     * @throws Exception
     */
    List<Compensate> selectBySelective(CompensateQueryDto compensateQueryDto) throws Exception;

    /**
     * 查询符合条件的数据条数
     * @param compensate
     * @return count
     * @throws Exception
     */
    int countBySelective(Compensate compensate) throws Exception;

    /**
     * 查询符合条件的数据条数
     * @param compensateQueryDto
     * @return count
     * @throws Exception
     */
    int countBySelective(CompensateQueryDto compensateQueryDto) throws Exception;
}
