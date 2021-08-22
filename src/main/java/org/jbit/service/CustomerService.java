package org.jbit.service;

import org.jbit.entity.Customer;

/**
 * @author yh
 * @version 1.0,2020-12-03
 */
public interface CustomerService {
    /**
     * 检验员工是否存在
     * @param customer
     * @return
     * @throws Exception
     */
    int isExist(Customer customer) throws Exception;

    /**
     * 注册新客户
     * @param customer
     * @return
     * @throws Exception
     */
    int create(Customer customer) throws Exception;

    /**
     * 按Id查找
     * @param id
     * @return
     * @throws Exception
     */
    Customer selectById(String id) throws Exception;
}
