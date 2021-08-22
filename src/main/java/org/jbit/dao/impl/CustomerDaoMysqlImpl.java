package org.jbit.dao.impl;

import org.jbit.dao.CustomerDao;
import org.jbit.entity.Customer;

import org.jbit.utils.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * @author jzt
 * @description 顾客实现类
 * @date 2020/11/26
 */
public class CustomerDaoMysqlImpl extends JdbcUtil implements CustomerDao {
    @Override
    public int insert(Customer obj) throws Exception {
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "INSERT INTO customer(Cid,Cname,Cpassword,Ctellnum)" + "VALUES(?,?,?,?)";
        //处理结果
        Object[] params = {

                obj.getCustomerId(),
                obj.getCustomerName(),
                obj.getCustomerPassword(),
                obj.getCustomerTelephoneNumber()
        };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int update(Customer obj) throws Exception {
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "UPDATE customer SET Cname=?, Cpassword=?, Ctellnum=? WHERE Cid=?";
        //处理结果
        Object[] params = {
                obj.getCustomerName(),
                obj.getCustomerPassword(),
                obj.getCustomerTelephoneNumber(),
                obj.getCustomerId(),
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
        String sql = "DELETE FROM customer WHERE Cid=?";
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
        String sql = "DELETE customer WHERE Rid=?";
        //处理结果
        Object[] params = {id};
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public Customer selectById(String id) throws Exception {
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Cid,Cname,Ctellnum,Cpassword FROM customer WHERE Cid=?";
        //处理结果
        Object[] params = {id};
        ResultSet rs = query(sql, params);
        Customer customer=null;
        while(rs.next()){
            customer = new Customer(
                    rs.getString("Cid"),
                    rs.getString("Cname"),
                    rs.getString("Cpassword"),
                    rs.getString("Ctellnum")
            );
        }

        //释放资源
        close();
        //返回结果
        return customer;
    }

    @Override
    public Customer selectById(Integer id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Cid,Cname,Ctellnum,Cpassword FROM customer WHERE Cid=?";
        //处理结果
        Object[] params = {id};
        ResultSet rs = query(sql, params);
        Customer customer=null;
        while(rs.next()){
            customer = new Customer(
                    rs.getString("Cid"),
                    rs.getString("Cname"),
                    rs.getString("Cpassword"),
                    rs.getString("Ctellnum")
            );
        }

        //释放资源
        close();
        //返回结果
        return customer;
    }

    @Override
    public List<Customer> selectBySelective(Customer obj) throws Exception {
        return null;
    }

    @Override
    public int countBySelective(Customer customer) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT count(1) count FROM customer WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, customer);
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

    private void getSelective(StringBuilder sql, List params, Customer customer) {
        /**
         * 根据条件拼接查询参数
         */
        if(customer.getCustomerId()!=null && !customer.getCustomerId().equals("")) {
            sql.append(" AND Cid LIKE CONCAT('%',?,'%')");
            params.add(customer.getCustomerId());
        }
        if(customer.getCustomerName()!=null && !customer.getCustomerName().equals("")) {
            sql.append(" AND Cname LIKE CONCAT('%',?,'%')");
            params.add(customer.getCustomerName());
        }
        if(customer.getCustomerPassword()!=null && !customer.getCustomerPassword().equals("")) {
            sql.append(" AND Cpassword LIKE CONCAT('%',?,'%')");
            params.add(customer.getCustomerPassword());
        }
        if(customer.getCustomerTelephoneNumber()!=null && !customer.getCustomerTelephoneNumber().equals("")) {
            sql.append(" AND Ctellnum LIKE CONCAT('%',?,'%')");
            params.add(customer.getCustomerTelephoneNumber());
        }
    }
}
