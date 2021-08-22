package org.jbit.service.impl;

import org.jbit.dao.CustomerDao;
import org.jbit.dao.impl.CustomerDaoMysqlImpl;
import org.jbit.entity.Customer;
import org.jbit.service.CustomerService;

/**
 * @author yh
 * @version 1.0,2020-12-03
 */
public class CustomerServiceImpl implements CustomerService {

    /**
     * 注入Dao
     */
    CustomerDao customerDao = new CustomerDaoMysqlImpl();

    @Override
    public int isExist(Customer customer) throws Exception {
        return customerDao.countBySelective(customer);
    }

    @Override
    public int create(Customer customer) throws Exception {
        return customerDao.insert(customer);
    }

    @Override
    public Customer selectById(String id) throws Exception {
        return customerDao.selectById(id);
    }

}
