package org.jbit.service;

import org.jbit.entity.Deposit;

public interface DepositService {
    /**
     * 存入账单
     * @param deposit
     */
    int insert(Deposit deposit) throws Exception;

    /**
     * 按id查询
     * @param orderId
     * @return
     */
    Deposit selectById(String orderId) throws Exception;

    /**
     * 修改信息
     * @param deposit
     */
    int save(Deposit deposit) throws Exception;
}
