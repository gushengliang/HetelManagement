package org.jbit.dao;

import org.jbit.entity.Position;

import java.util.List;

/**
 * 职务接口
 *
 * @author yh
 *
 * @version 1.0,2020-11-23
 */
public interface PositionDao extends BaseDao {
    /**
     * 获取职务列表
     * @return
     * @throws Exception
     */
    List<Position> getAllPositions() throws Exception;
}
