package org.jbit.dao.impl;

import org.jbit.dao.UserDao;
import org.jbit.entity.User;
import org.jbit.utils.JdbcUtil;

import java.sql.ResultSet;
import java.util.List;

/**
 * UserDao实现类
 * @author yh
 * @version 1.0,2020-12-02
 */
public class UserDaoMysqlImpl extends JdbcUtil implements UserDao {
    @Override
    public User selectCustomerByUserName(String username) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        //String sql="SELECT Cid,Cname,Cpassword FROM customer WHERE Cname=?";
        String sql="SELECT Cid,Cname,Cpassword FROM customer WHERE Cid=?";

        Object[] params={username};
        //处理结果
        ResultSet rs=query(sql, params);

        User user=null;

        if(rs.next()){
            user=new User();
            user.setId(rs.getString("Cid"));
            user.setName(rs.getString("Cname"));
            user.setUsername(rs.getString("Cname"));
            user.setPassword(rs.getString("Cpassword"));
            user.setType(2);
        }
        return user;
    }

    @Override
    public User selectAdministratorByUserName(String username) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        //String sql="SELECT Aid,Aname,Apassword FROM admin WHERE Aname=?";
        String sql="SELECT Aid,Aname,Apassword FROM admin WHERE Aid=?";

        Object[] params={username};
        //处理结果
        ResultSet rs=query(sql, params);

        User user=null;

        if(rs.next()){
            user=new User();
            user.setId(rs.getString("Aid"));
            user.setName(rs.getString("Aname"));
            user.setUsername(rs.getString("Aname"));
            user.setPassword(rs.getString("Apassword"));
            user.setType(1);
        }
        return user;
    }

    @Override
    public User selectStaffByUserName(String username) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        //String sql="SELECT Sid,Sname,Spassword FROM staff WHERE Sname=?";
        String sql = "SELECT Sid,Sname,Spassword FROM staff WHERE Sid=?";

        Object[] params = {username};
        //处理结果
        ResultSet rs = query(sql, params);

        User user = null;

        if(rs.next()){
            user = new User();
            user.setId(rs.getString("Sid"));
            user.setName(rs.getString("Sname"));
            user.setUsername(rs.getString("Sname"));
            user.setPassword(rs.getString("Spassword"));
            user.setType(3);
        }
        return user;
    }

    @Override
    public int changeCustomerPassword(User user) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        //String sql="SELECT Sid,Sname,Spassword FROM staff WHERE Sname=?";
        String sql = "UPDATE customer SET Cpassword=? WHERE Cid=?";

        Object[] params={user.getPassword(), user.getId()};
        //处理结果
        int rows = update(sql, params);

        return rows;
    }

    @Override
    public int changeStaffPassword(User user) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        //String sql="SELECT Sid,Sname,Spassword FROM staff WHERE Sname=?";
        String sql="UPDATE staff SET Spassword=? WHERE Sid=?";

        Object[] params={user.getPassword(), user.getId()};
        //处理结果
        int rows = update(sql, params);

        return rows;
    }

    @Override
    public int changeAdministratorPassword(User user) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        //String sql="SELECT Sid,Sname,Spassword FROM staff WHERE Sname=?";
        String sql="UPDATE admin SET Apassword=? WHERE Aid=?";

        Object[] params={user.getPassword(), user.getId()};
        //处理结果
        int rows = update(sql, params);

        return rows;
    }

    @Override
    public int insert(User obj) throws Exception {
        return 0;
    }

    @Override
    public int update(User obj) throws Exception {
        return 0;
    }

    @Override
    public int delete(String id) throws Exception {
        return 0;
    }

    @Override
    public int delete(Integer id) throws Exception {
        return 0;
    }

    @Override
    public User selectById(String id) throws Exception {
        return null;
    }

    @Override
    public User selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<User> selectBySelective(User obj) throws Exception {
        return null;
    }
}
