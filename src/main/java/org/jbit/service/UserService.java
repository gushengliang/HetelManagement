package org.jbit.service;

import org.jbit.entity.User;

/**
 * 用户Service接口
 * @author yh
 * @version 1.0,2020-12-02
 */
public interface UserService {
    /**
     *按用户名查找客户
     * @param username
     * @return
     * @throws Exception
     */
    User findCustomerByUserName(String username) throws Exception;

    /**
     *按用户名查找员工
     * @param username
     * @return
     * @throws Exception
     */
    User findStaffByUserName(String username) throws Exception;

    /**
     * 按用户名查找管理员
     * @param username
     * @return
     * @throws Exception
     */
    User findAdministratorByUserName(String username) throws Exception;

    /**
     * 修改顾客密码
     * @param user
     * @return
     * @throws Exception
     */
    int changeCustomerPassword(User user) throws Exception;

    /**
     * 修改员工密码
     * @param user
     * @return
     * @throws Exception
     */
    int changeStaffPassword(User user) throws Exception;

    /**
     * 修改管理员密码
     * @param user
     * @return
     * @throws Exception
     */
    int changeAdministratorPassword(User user) throws Exception;
}
