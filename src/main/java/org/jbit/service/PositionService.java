package org.jbit.service;

import org.jbit.entity.Position;

import java.util.List;

public interface PositionService {

    /**
     * 获取职务列表
     * @return
     * @throws Exception
     */
    List<Position> getAllPositions() throws Exception;
}
