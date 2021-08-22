package org.jbit.service.impl;

import org.jbit.dao.UserDao;
import org.jbit.dao.impl.UserDaoMysqlImpl;
import org.jbit.entity.User;
import org.jbit.service.UserService;

/**
 * 用户Service实现类
 * @author yh
 * @version 1.0,2020-12-02
 */
public class UserServiceImpl implements UserService {

    /**
     * 注入Dao
     */
    UserDao userDao = new UserDaoMysqlImpl();

    @Override
    public User findCustomerByUserName(String username) throws Exception {
        return userDao.selectCustomerByUserName(username);
    }

    @Override
    public User findStaffByUserName(String username) throws Exception {
        return userDao.selectStaffByUserName(username);
    }

    @Override
    public User findAdministratorByUserName(String username) throws Exception {
        return userDao.selectAdministratorByUserName(username);
    }

    @Override
    public int changeCustomerPassword(User user) throws Exception {
        return userDao.changeCustomerPassword(user);
    }

    @Override
    public int changeStaffPassword(User user) throws Exception {
        return userDao.changeStaffPassword(user);
    }

    @Override
    public int changeAdministratorPassword(User user) throws Exception {
        return userDao.changeAdministratorPassword(user);
    }
}
