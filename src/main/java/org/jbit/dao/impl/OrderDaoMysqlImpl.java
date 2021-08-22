package org.jbit.dao.impl;

import org.jbit.dao.CustomerDao;
import org.jbit.dao.OrderDao;
import org.jbit.dao.RoomDao;
import org.jbit.dto.OrderQueryDto;
import org.jbit.entity.Customer;
import org.jbit.entity.Order;
import org.jbit.entity.Room;
import org.jbit.utils.JdbcUtil;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jzt
 * @description 订单实现类
 * @date 2020/11/29
 */
public class OrderDaoMysqlImpl extends JdbcUtil implements OrderDao {
    @Override
    public int insert(Order obj) throws Exception {
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "INSERT INTO `order`(Oid,Cid,Rid,Begin_time,Finish_time)" + "VALUES(?,?,?,?,?)";
        //处理结果
        //格式化时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Timestamp Begin_time = new Timestamp(obj.getBeginTime().getTime());
        Timestamp Finish_time = new Timestamp(obj.getOverTime().getTime());
        Object[] params = {
                obj.getOrderId(),
                obj.getCustomer().getCustomerId(),
                obj.getRoom().getRoomId(),
                format.format(Begin_time),
                format.format(Finish_time),
        };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int update(Order obj) throws Exception {
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "UPDATE `order` SET Cid=?, Rid=?, Begin_time=?,Finish_time=? WHERE Oid=?";
        //处理结果

        //格式化时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Timestamp Begin_time = new Timestamp(obj.getBeginTime().getTime());
        Timestamp Finish_time = new Timestamp(obj.getOverTime().getTime());
        Object[] params = {
                obj.getCustomer().getCustomerId(),
                obj.getRoom().getRoomId(),
                format.format(Begin_time),
                format.format(Finish_time),
                obj.getOrderId()
        };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int delete(String id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "DELETE FROM `order` WHERE Cid=?";
        //处理结果
        Object[] params = {id};
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int delete(Integer id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "DELETE FROM `order` WHERE Rid=?";
        //处理结果
        Object[] params = {id};
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public Order selectById(String id) throws Exception {
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Oid,Rid,Cid,Begin_time,Finish_time FROM `order` WHERE Oid=?";
        //处理结果
        Object[] params = {id};
        ResultSet rs = query(sql, params);
        /**
         * 生成顾客和客房对象
         */
        RoomDao roomdao = new RoomDaoMysqlImpl();
        CustomerDao customerDao = new CustomerDaoMysqlImpl();
        Order order = null;
        while(rs.next()){
            Room room = roomdao.selectById(rs.getString("Rid"));

            Customer customer = customerDao.selectById(rs.getString("Cid"));
            order = new Order(
                    customer,
                    room
            );
            order.setBeginTime(toDate(String.valueOf(rs.getTimestamp("Begin_time"))));
            order.setOverTime(toDate(String.valueOf(rs.getTimestamp("Finish_time"))));
            order.setOrderId(rs.getString("Oid"));
        }
        //释放资源
        close();
        //返回结果
        return order;
    }

    @Override
    public Order selectById(Integer id) throws Exception {
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Oid,Rid,Cid,Begin_time,Finish_time FROM `order` WHERE Oid=?";
        //处理结果
        Object[] params = {id};
        ResultSet rs = query(sql, params);
        /**
         * 生成顾客和客房对象
         */
        RoomDao roomdao = new RoomDaoMysqlImpl();
        CustomerDao customerDao = new CustomerDaoMysqlImpl();
        Order order = null;
        while(rs.next()){
            Room room = roomdao.selectById(rs.getString("Rid"));
            Customer customer = customerDao.selectById(rs.getString("Cid"));
            order = new Order(
                    customer,
                    room
            );
            order.setBeginTime(toDate(String.valueOf(rs.getTimestamp("Begin_time"))));
            order.setOverTime(toDate(String.valueOf(rs.getTimestamp("Finish_time"))));
            order.setOrderId(rs.getString("Oid"));
        }

        //释放资源
        close();
        //返回结果
        return order;
    }

    @Override
    public int countBySelective(OrderQueryDto orderQueryDto) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT count(1) count FROM `order` WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, orderQueryDto);
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        int count = 0;
        if (rs.next()) {
            count = rs.getInt("count");
        }
        //释放资源
        close();
        //返回结果
        return count;
    }

    @Override
    public List<Order> ExeOrder(String customerId) throws Exception {
        //定义参数数组
        List Orders = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT * FROM `order` WHERE Cid=?";
        Object[] params = {customerId};
        ResultSet rs = query(sql, params);

        RoomDao roomdao = new RoomDaoMysqlImpl();
        CustomerDao customerDao = new CustomerDaoMysqlImpl();
        Order order = null;
        Date date=new Date();

        while(rs.next()){
            if(toDate(String.valueOf(rs.getTimestamp("Finish_time"))).getTime()>date.getTime()){
                Room room = roomdao.selectById(rs.getString("Rid"));
                Customer customer = customerDao.selectById(rs.getString("Cid"));
                order = new Order(
                        customer,
                        room
                );
                order.setBeginTime(toDate(String.valueOf(rs.getTimestamp("Begin_time"))));
                order.setOverTime(toDate(String.valueOf(rs.getTimestamp("Finish_time"))));
                order.setOrderId(rs.getString("Oid"));
                Orders.add(order);
            }

        }

        //释放资源
        close();
        //返回结果
        return Orders;
    }

    @Override
    public List<Order> OverOrder(String customerId) throws Exception {
        //定义参数数组
        List Orders = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT * FROM `order` WHERE Cid=?";
        Object[] params = {customerId};
        ResultSet rs = query(sql, params);

        RoomDao roomdao = new RoomDaoMysqlImpl();
        CustomerDao customerDao = new CustomerDaoMysqlImpl();
        Order order = null;
        Date date=new Date();

        while(rs.next()){
            if(date.getTime()>toDate(String.valueOf(rs.getTimestamp("Finish_time"))).getTime()){
                Room room = roomdao.selectById(rs.getString("Rid"));
                Customer customer = customerDao.selectById(rs.getString("Cid"));
                order = new Order(
                        customer,
                        room
                );
                order.setBeginTime(toDate(String.valueOf(rs.getTimestamp("Begin_time"))));
                order.setOverTime(toDate(String.valueOf(rs.getTimestamp("Finish_time"))));
                order.setOrderId(rs.getString("Oid"));
                Orders.add(order);
            }

        }

        //释放资源
        close();
        //返回结果
        return Orders;
    }


    public void getSelective(StringBuilder sql, List params, Order order) {
        /**
         * 根据条件拼接查询参数
         */
        if (order.getRoom().getRoomId() != null && !order.getRoom().getRoomId().equals("")) {
            sql.append(" AND Rid =?");
            params.add(order.getRoom().getRoomId());
        }
        if (order.getCustomer().getCustomerId() != null && !order.getCustomer().getCustomerId() .equals("")) {
            sql.append(" AND Cid =?");
            params.add(order.getCustomer().getCustomerId() );
        }
        if(order.getOrderId()!=null && !order.getOrderId().equals("")) {
            sql.append(" AND Rid =?");
            params.add(order.getOrderId());
        }

    }

    private void getResultSet(ResultSet rs, List orders) throws Exception {
        /**
         * 处理结果
         */
            RoomDao roomdao = new RoomDaoMysqlImpl();
            CustomerDao customerDao = new CustomerDaoMysqlImpl();
            Order order = null;
            while(rs.next()){

                Room room = roomdao.selectById(rs.getString("Rid"));
                Customer customer = customerDao.selectById(rs.getString("Cid"));
                order = new Order(
                        customer,
                        room
                );
                order.setBeginTime(toDate(String.valueOf(rs.getTimestamp("Begin_time"))));
                order.setOverTime(toDate(String.valueOf(rs.getTimestamp("Finish_time"))));
                order.setOrderId(rs.getString("Oid"));
                orders.add(order);
            }
    }


    @Override
    public List<Order> selectBySelective(Order order) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT Cid,Rid,Oid,Begin_time,Finish_time FROM `order` WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, order);
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        List<Order> orders = new ArrayList<Order>();

        getResultSet(rs, orders);
        //释放资源
        close();
        //返回结果
        return orders;
    }



    public Date toDate(String datetime) throws ParseException {
        String pattern="yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
        Date date=dateFormat.parse(datetime);
        return date;
    }
}
