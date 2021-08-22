package org.jbit.service.impl;

import org.jbit.dao.PositionDao;
import org.jbit.dao.impl.PositionDaoMysqlImpl;
import org.jbit.entity.Position;
import org.jbit.service.PositionService;

import java.util.List;

public class PositionServiceImpl implements PositionService {
    /**
     * 注入Dao
     */
    PositionDao positionDao = new PositionDaoMysqlImpl();

    @Override
    public List<Position> getAllPositions() throws Exception {
        return positionDao.getAllPositions();
    }
}
