package org.jbit.dao;

import org.jbit.entity.User;

/**
 * UserDao
 * @author yh
 * @version 1.0,2020-12-02
 */
public interface UserDao extends BaseDao<User> {
    /**
     * 按用户名搜索客户
     * @param username
     * @return
     * @throws Exception
     */
    User selectCustomerByUserName(String username) throws Exception;

    /**
     *按用户名搜索员工
     * @param username
     * @return
     * @throws Exception
     */
    User selectStaffByUserName(String username) throws Exception;

    /**
     *按用户名搜索管理员
     * @param username
     * @return
     * @throws Exception
     */
    User selectAdministratorByUserName(String username) throws Exception;

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
