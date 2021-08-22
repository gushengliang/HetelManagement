package org.jbit.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.ietf.jgss.Oid;
import org.jbit.dao.CustomerDao;
import org.jbit.dao.OrderDao;
import org.jbit.dao.RoomDao;
import org.jbit.dao.impl.CustomerDaoMysqlImpl;
import org.jbit.dao.impl.OrderDaoMysqlImpl;
import org.jbit.dao.impl.RoomDaoMysqlImpl;
import org.jbit.dto.OrderQueryDto;
import org.jbit.entity.Customer;
import org.jbit.entity.Deposit;
import org.jbit.entity.Order;
import org.jbit.entity.Room;
import org.jbit.service.DepositService;
import org.jbit.service.OrderService;
import org.jbit.service.RoomService;
import org.jbit.service.impl.DepositServiceImpl;
import org.jbit.service.impl.OrderServiceImpl;
import org.jbit.service.impl.RoomServiceImpl;
import org.jbit.utils.JdbcUtil;
import org.jbit.utils.PageUtil;
import org.jbit.vo.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * @author jzt
 * @description
 * @date 2020/12/1
 */

@WebServlet("/order")
public class OrderController extends BaseController{
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);

    /**
     * 注入Service
     */
    RoomService roomService=new RoomServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    DepositService depositService=new DepositServiceImpl();

    /**
     * 数据列表
     *
     * @param req
     * @param res
     */
    public void ExeOrder_list(HttpServletRequest req, HttpServletResponse res) throws Exception {
        sleep(1000);
        String customerId = req.getParameter("customerId");

        //处理数据
        ResultVo resultVo = null;
        try {
            //调用Service
            List<Order> orders= orderService.ExeOrder(customerId);
            resultVo = new ResultVo<>(orders);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(500, "未知服务错误");
        }

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);

    }
    public void OverOrder_list(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String customerId = req.getParameter("customerId");

        //处理数据
        ResultVo resultVo = null;
        try {
            //调用Service
            List<Order> orders= orderService.OverOrder(customerId);
            resultVo = new ResultVo<>(orders);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(500, "未知服务错误");
        }
        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);


    }

    public void ChangeRoom(HttpServletRequest req, HttpServletResponse res) throws Exception{
        String RoomId=req.getParameter("Rid");
        String OrderId=req.getParameter("Oid");
        //生成新的房间

        Room room=roomService.selectById(RoomId);
        //查询订单
        Order order=orderService.selectById(OrderId);

        Deposit deposit=depositService.selectById(OrderId);

        order.setRoom(room);
        deposit.setOrder(order);
        deposit.count();

        ResultVo resultVo=null;
        try{
            orderService.save(order);
            depositService.save(deposit);
            resultVo = new ResultVo(0, "成功");
        }catch (Exception e){
            resultVo = new ResultVo(500, "失败");
        }

        String json = JSON.toJSONString(resultVo);
        /**
         *  响应结果
         */

        PrintWriter out = res.getWriter();
        out.print(json);

    }
    public void Faults(HttpServletRequest req, HttpServletResponse res) throws Exception{
        String OrderId=req.getParameter("Oid");
        Date date=new Date();
        //查询订单
        Order order=orderService.selectById(OrderId);

        Deposit deposit=depositService.selectById(OrderId);

        order.setOverTime(date);
        deposit.setOrder(order);
        deposit.count();

        ResultVo resultVo=null;
        try{
            orderService.save(order);
            depositService.save(deposit);
            resultVo = new ResultVo(0, "成功");
        }catch (Exception e){
            resultVo = new ResultVo(500, "失败");
        }

        String json = JSON.toJSONString(resultVo);
        /**
         *  响应结果
         */

        PrintWriter out = res.getWriter();
        out.print(json);



    }
}
