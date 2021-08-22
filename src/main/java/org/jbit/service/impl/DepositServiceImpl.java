package org.jbit.service.impl;

import org.jbit.dao.DepositDao;
import org.jbit.dao.impl.DespositDaoMysqlImpl;
import org.jbit.entity.Deposit;
import org.jbit.service.DepositService;

/**
 * @author jzt
 * @description
 * @date 2020/12/14
 */
public class DepositServiceImpl implements DepositService {
    private DepositDao depositDao=new DespositDaoMysqlImpl();

    /**
     * 插入新账单
     * @param deposit
     * @return
     * @throws Exception
     */
    @Override
    public int insert(Deposit deposit) throws Exception {
        return depositDao.insert(deposit);
    }

    /**
     * 按id查询
     * @param orderId
     * @return
     */
    @Override
    public Deposit selectById(String orderId) throws Exception {
        return depositDao.selectById(orderId);
    }

    /**
     * 修改信息
     * @param deposit
     */
    @Override
    public int save(Deposit deposit) throws Exception {
        return depositDao.update(deposit);
    }
}
