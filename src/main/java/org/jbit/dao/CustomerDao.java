package org.jbit.dao;

import org.jbit.entity.Customer;
/**
 * 顾客接口
 *
 * @author jzt
 *
 * @version 1.0,2020-11-26
 */
public interface CustomerDao extends BaseDao<Customer>{
    /**
     * 按照Id
     * @param customer
     * @return
     * @throws Exception
     */
    int countBySelective(Customer customer) throws Exception;
}
