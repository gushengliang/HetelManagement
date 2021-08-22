package org.jbit.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.jbit.entity.Deposit;
import org.jbit.entity.Order;
import org.jbit.service.DepositService;
import org.jbit.service.impl.DepositServiceImpl;
import org.jbit.utils.JdbcUtil;
import org.jbit.vo.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author jzt
 * @description
 * @date 2020/12/14
 */
@WebServlet("/deposit")
public class DepositController extends BaseController{
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);

    /**
     * 注入Service
     */
    DepositService depositService=new DepositServiceImpl();

    /**
     * 查询订单
     * @param req
     * @param res
     * @throws Exception
     */
    public void GetOne(HttpServletRequest req, HttpServletResponse res) throws Exception{
        String OrderId = req.getParameter("OrderId");
        //处理数据
        ResultVo resultVo = null;
        try {
            //调用Service
            Deposit deposit=depositService.selectById(OrderId);
            resultVo = new ResultVo<>(deposit);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(500, "未知服务错误");
        }

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }
    public void Payment(HttpServletRequest req, HttpServletResponse res) throws Exception{
        String OrderId = req.getParameter("OrderId");
        //处理数据
        ResultVo resultVo = null;
        Deposit deposit=new Deposit();
        deposit=depositService.selectById(OrderId);
        deposit.setDepositState("1");
        try{
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
