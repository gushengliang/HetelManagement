package org.jbit.dao.impl;

import org.jbit.dao.CustomerDao;
import org.jbit.dao.DepositDao;
import org.jbit.dao.OrderDao;
import org.jbit.dao.RoomDao;
import org.jbit.entity.Customer;
import org.jbit.entity.Deposit;
import org.jbit.entity.Order;
import org.jbit.entity.Room;
import org.jbit.utils.JdbcUtil;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author jzt
 * @description 账单实现类
 * @date 2020/12/14
 */
public class DespositDaoMysqlImpl extends JdbcUtil implements DepositDao {


    @Override
    public int insert(Deposit obj) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "INSERT INTO deposit(Oid,Dprice,Dstate)" + "VALUES(?,?,?)";
        //处理结果
        Object[] params = {
                obj.getOrder().getOrderId(),
                obj.getDepositPrice(),
                obj.getDepositState()
        };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int update(Deposit obj) throws Exception {
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "UPDATE deposit SET   Dprice=?,Dstate=? WHERE Oid=?";
        //处理结果
        Object[] params = {
                obj.getDepositPrice(),
                obj.getDepositState(),
                obj.getOrder().getOrderId()
        };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
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
    public Deposit selectById(String id) throws Exception {
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Oid,Dprice,Dstate FROM deposit WHERE Oid=?";
        //处理结果
        Object[] params = {id};
        ResultSet rs = query(sql, params);
        /**
         * 生成顾客和客房对象
         */
        OrderDao orderDao=new OrderDaoMysqlImpl();
        Deposit deposit=new Deposit();
        while(rs.next()){

            Order order=orderDao.selectById(rs.getString("Oid"));
            deposit.setOrder(order);

            deposit.setDepositState(rs.getString("Dstate"));
            deposit.setDepositPrice(rs.getFloat("Dprice"));
        }
        //释放资源
        close();
        //返回结果
        return deposit;

    }

    @Override
    public Deposit selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Deposit> selectBySelective(Deposit obj) throws Exception {
        return null;
    }
}
