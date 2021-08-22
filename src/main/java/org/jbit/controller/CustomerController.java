package org.jbit.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.jbit.entity.Customer;
import org.jbit.service.CustomerService;
import org.jbit.service.RoomService;
import org.jbit.service.impl.CustomerServiceImpl;
import org.jbit.service.impl.RoomServiceImpl;
import org.jbit.utils.JdbcUtil;
import org.jbit.vo.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author jzt
 * @description
 * @date 2020/11/29
 */

@WebServlet("/customer")
public class CustomerController extends BaseController{
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);

    /**
     * 注入Service
     */
    RoomService roomService = new RoomServiceImpl();
    CustomerService customerService = new CustomerServiceImpl();

    /**
     * 数据列表
     *
     * @param req
     * @param res
     */

    private void getCustomer(HttpServletRequest req, HttpServletResponse res) throws Exception{
        //获取客户ID
        String customerID=req.getParameter("customerId");
        //查询得到客户类
        ResultVo resultVo=null;
        try{
            Customer customer = customerService.selectById(customerID);
            resultVo = new ResultVo<>(customer);

        }catch (Exception e){
            e.printStackTrace();
            resultVo = new ResultVo(500, "未知服务错误");
        }

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }

    /**
     * 判断客户Id是否已存在
     * @param req
     * @param res
     * @throws Exception
     */
    private void isExist(HttpServletRequest req, HttpServletResponse res) throws Exception{
        //获取参数
        String userId = req.getParameter("userId");
        //封装对象
        Customer customer = new Customer();
        customer.setCustomerId(userId);

        //保存数据
        ResultVo resultVo = null;
        //将对象封装到resultVo
        try {
            if(customerService.isExist(customer) == 0) {
                resultVo= new ResultVo<>(customer);
            }
            else if (customerService.isExist(customer) > 0){
                resultVo = new ResultVo(501, "该用户已存在");
            }
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
     * 注册新客户
     * @param req
     * @param res
     * @throws Exception
     */
    private void create(HttpServletRequest req, HttpServletResponse res) throws Exception{
        String userId = req.getParameter("userId");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String telNumber = req.getParameter("telNumber");

        //密码加密
        String token= DigestUtils.md5Hex(password);

        Customer customer = new Customer(userId, userName, token, telNumber);

        int rows = customerService.create(customer);

        ResultVo resultVo = new ResultVo(rows);

        String json = JSON.toJSONString(resultVo);
        PrintWriter out = res.getWriter();
        out.print(json);
    }
}
