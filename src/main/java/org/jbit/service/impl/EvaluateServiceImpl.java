package org.jbit.service.impl;

import org.jbit.dao.EvaluateDao;
import org.jbit.dao.impl.EvaluateDaoMysqlImpl;
import org.jbit.entity.Evaluate;
import org.jbit.entity.Order;
import org.jbit.service.EvaluateService;

import java.util.List;

public class EvaluateServiceImpl implements EvaluateService {

    EvaluateDao evaluateDao=new EvaluateDaoMysqlImpl();

    @Override
    public int save(Evaluate evaluate) throws Exception {
        return evaluateDao.update(evaluate);
    }

    @Override
    public Order selectById(String id) throws Exception {
        return null;
    }

    @Override
    public int create(Evaluate evaluate) throws Exception {
        return evaluateDao.insert(evaluate);
    }
    @Override
    public boolean countById(String id) throws Exception {
        return evaluateDao.countById(id);
    }

    @Override
    public List<Evaluate> selectByRoomId(String roomId) throws Exception {
        return evaluateDao.selectByRoomId(roomId);
    }
}
