package org.jbit.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.jbit.dao.CustomerDao;
import org.jbit.dao.OrderDao;
import org.jbit.dao.RoomDao;
import org.jbit.dao.impl.CustomerDaoMysqlImpl;
import org.jbit.dao.impl.OrderDaoMysqlImpl;
import org.jbit.dao.impl.RoomDaoMysqlImpl;
import org.jbit.dto.RoomQueryDto;
import org.jbit.entity.*;
import org.jbit.service.DepositService;
import org.jbit.service.RoomService;
import org.jbit.service.impl.DepositServiceImpl;
import org.jbit.service.impl.RoomServiceImpl;
import org.jbit.utils.JdbcUtil;
import org.jbit.utils.PageUtil;
import org.jbit.utils.StringUtil;
import org.jbit.vo.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 李宇杰
 * @description
 * @date 2020/11/23 0023
 */

@WebServlet("/room")
public class RoomController extends BaseController {

    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);

    /**
     * 注入Service
     */
    RoomService roomService = new RoomServiceImpl();

    /**
     * 数据列表
     *
     * @param req
     * @param res
     */
    private void list(HttpServletRequest req, HttpServletResponse res) throws Exception {

        //接受请求
        String roomId = req.getParameter("roomId");
        String roomName = req.getParameter("roomName");
        String roomType = req.getParameter("roomType");
        String roomPrice = req.getParameter("roomPrice");
        Integer roomState = StringUtil.toInt(req.getParameter("roomState"));
        Integer currentPage = Integer.parseInt(req.getParameter("currentPage"));
        Integer pageSize = Integer.parseInt(req.getParameter("pageSize"));

        //封装对象
        RoomQueryDto roomQueryDto = new RoomQueryDto(
                roomId,
                roomName,
                roomType,
                roomPrice,
                roomState,
                currentPage,
                pageSize
        );
        //处理数据
        PageUtil pageUtil = null;
        ResultVo resultVo = null;
        try {
            //调用Service
            pageUtil = roomService.pageList(roomQueryDto);
            resultVo = new ResultVo<>(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(500, "未知服务错误");
        }

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }
    private void BookRoom(HttpServletRequest req, HttpServletResponse res) throws Exception{
        /**
         * 获取参数
         */
        String RoomId= req.getParameter("roomId");
        String CustomerId= req.getParameter("customerId");
        String BeginTime=req.getParameter("begin_time");
        String FinishTime=req.getParameter("finish_time");
        /**
         * 生成顾客和客房对象
         */
        RoomDao roomdao=new RoomDaoMysqlImpl();
        CustomerDao customerDao=new CustomerDaoMysqlImpl();
        DepositService depositService=new DepositServiceImpl();
        Deposit deposit=new Deposit();
        Room room=roomdao.selectById(RoomId);
        Customer customer=customerDao.selectById(CustomerId);
        /**
         * 生成订单及账单
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Order order =new Order(customer,room);
        order.setBeginTime(sdf.parse(BeginTime));
        order.setOverTime(sdf.parse(FinishTime));
        deposit.setOrder(order);
        deposit.setDepositState("0");
        deposit.count();
        /**
         * 存入订单
         */
        OrderDao orderdao=new OrderDaoMysqlImpl();
        ResultVo resultVo=null;
        try{
            orderdao.insert(order);
            depositService.insert(deposit);
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

    /**
     * 占用房间
     * @param req
     * @param res
     * @throws Exception
     */
    private void occupyRoom(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //接收请求
        String roomId = req.getParameter("roomId");
        //调用service方法
        roomService.occupyById(roomId);

        int rows = 0;
        ResultVo resultVo = null;

        try {
            rows = roomService.occupyById(roomId);
            resultVo = new ResultVo<>(rows);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(500, "未知服务错误");
        }

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }

    /**
     * 添加房间信息
     * @param req
     * @param res
     * @throws Exception
     */
    private void create(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String roomId = req.getParameter("roomId");
        String roomName = req.getParameter("roomName");
        String roomType = req.getParameter("roomType");
        String roomPrice = req.getParameter("roomPrice");
        //Integer roomState = Integer.valueOf(req.getParameter("roomState"));

        Room room = new Room(roomId, roomName, roomType, roomPrice);

        int rows = roomService.create(room);

        ResultVo resultVo = new ResultVo(rows);

        String json = JSON.toJSONString(resultVo);
        PrintWriter out = res.getWriter();
        out.print(json);
    }

    /**
     * 批量占用房间
     * @param req
     * @param res
     * @throws Exception
     */
    private void deleteMany(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String[] multipleSelections = req.getParameterValues("multipleSelection");
        int rows = 0;
        ResultVo resultVo = null;

        try {
            //调用roomService
            for(String roomId : multipleSelections) {
                rows += roomService.occupyById(roomId);
            }
            resultVo = new ResultVo<>(rows);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(500, "未知服务错误");
        }

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }

    /**
     * 校验房间已存在
     * @param req
     * @param res
     * @throws Exception
     */
    private void isExist(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //获取参数
        String roomId = req.getParameter("roomId");
        //封装对象
        Room room = new Room();
        room.setRoomId(roomId);

        //保存数据
        ResultVo resultVo = null;
        //将对象封装到resultVo
        try {
            if(roomService.isExist(room) == 0) {
                resultVo = new ResultVo<>(room);
            }else if (roomService.isExist(room) > 0) {
                resultVo = new ResultVo(501, "该房间已经存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(500, "未知服务错误");
        }
        //回传
        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }

    /**
     * 保存房间信息
     * @param req
     * @param res
     * @throws Exception
     */
    private void save(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String roomId = req.getParameter("roomId");
        String roomName =req.getParameter("roomName");
        String roomType =req.getParameter("roomType");
        String roomPrice = req.getParameter("roomPrice");
        Integer roomState = Integer.valueOf(req.getParameter("roomState"));

        Room room = new Room();
        room.setRoomId(roomId);
        room.setRoomName(roomName);
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        room.setRoomState(roomState);

        int rows = roomService.save(room);

        ResultVo resultVo = new ResultVo(rows);

        String json = JSON.toJSONString(resultVo);
        PrintWriter out = res.getWriter();
        out.print(json);
    }

    /**
     * 获取房间设施
     * @param req
     * @param res
     * @throws Exception
     */
    private void getFacilities(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //获取参数
        String roomId = req.getParameter("roomId");
        //封装对象
        List<Facility> facilities = roomService.getFacilities(roomId);
        //将对象封装到ResultVo
        ResultVo resultVo = new ResultVo(facilities);
        //响应结果
        String json = JSON.toJSONString(resultVo);
        PrintWriter out = res.getWriter();
        out.print(json);
    }

    private void editFacilities(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //获取参数
        String roomId = req.getParameter("roomId");
        String[] multipleSelections = req.getParameterValues("multipleSelection");
        //封装对象

        int rows = 0;
        int deleteRows = 0;
        ResultVo resultVo = null;

        deleteRows = roomService.deleteFacilities(roomId);
        if(deleteRows >= 0) {
            //将对象封装到ResultVo
            try {
                for(String facilityId: multipleSelections) {
                    Facility facility = new Facility();
                    facility.setFacilityId(facilityId);
                    rows += roomService.addFacilities(roomId, facility);
                    resultVo = new ResultVo<>(rows);
                }
            } catch (Exception e) {
                e.printStackTrace();
                resultVo = new ResultVo(500, "未知服务错误");
            }
        }
        //响应结果
        String json = JSON.toJSONString(resultVo);
        PrintWriter out = res.getWriter();
        out.print(json);
    }

}

