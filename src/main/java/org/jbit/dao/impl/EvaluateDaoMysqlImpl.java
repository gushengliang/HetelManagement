package org.jbit.dao.impl;

import org.jbit.dao.EvaluateDao;
import org.jbit.entity.Evaluate;
import org.jbit.entity.Order;
import org.jbit.entity.Room;
import org.jbit.service.OrderService;
import org.jbit.service.impl.OrderServiceImpl;
import org.jbit.utils.JdbcUtil;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * @author jzt
 * @description 评价单实现类
 * @date 2020/12/4
 */
public class EvaluateDaoMysqlImpl extends JdbcUtil implements EvaluateDao {
    @Override
    public List<Evaluate> getAllEvaluate() throws Exception {
        return null;
    }

    @Override
    public boolean countById(String id) throws Exception {
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT count(1) count FROM evaluate WHERE Oid=?";
        //处理结果

        Object[] params = {id};
        ResultSet rs = query(sql, params);
        int count = 0;
        if (rs.next()) {
            count = rs.getInt("count");
        }
        boolean rows;
        if(count==0){
            rows=false;
        }else{
            rows=true;
        }
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public List<Evaluate> selectByRoomId(String roomId) throws Exception {

        OrderService orderService=new OrderServiceImpl();

        getConnection("db_mysql.properties");
        String sql = "SELECT * FROM evaluate,`order` WHERE evaluate.Oid=`order`.Oid AND `order`.Rid=?";
        Object[] params = {roomId};
        ResultSet rs = query(sql, params);

        List<Evaluate> evaluates = new ArrayList<Evaluate>();
        while (rs.next()){
            Order order=orderService.selectById(rs.getString("Oid"));
            Evaluate evaluate=new Evaluate(order,rs.getString("Econtent"),rs.getInt("Elevel"));
            evaluates.add(evaluate);
        }
        return evaluates;
    }

    @Override
    public int insert(Evaluate obj) throws Exception {
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "INSERT INTO `evaluate`(Oid,Econtent,Elevel)  VALUES(?,?,?)";
        //处理结果



        Object[] params = {
                obj.getOrder().getOrderId(),
                obj.getEvaluateContent(),
                obj.getEvaluateLevel()

        };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int update(Evaluate obj) throws Exception {
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "UPDATE `evaluate` SET Oid=?, Econtent=?, Elevel=? WHERE Oid=?";
        //处理结果

        Object[] params = {
                obj.getOrder().getOrderId(),
                obj.getEvaluateContent(),
                obj.getEvaluateLevel(),
                obj.getOrder().getOrderId(),
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
    public Evaluate selectById(String id) throws Exception {
        return null;
    }

    @Override
    public Evaluate selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List selectBySelective(Evaluate obj) throws Exception {
        return null;
    }
}
