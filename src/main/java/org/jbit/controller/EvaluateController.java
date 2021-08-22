package org.jbit.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.jbit.entity.Evaluate;
import org.jbit.entity.Order;
import org.jbit.service.EvaluateService;
import org.jbit.service.OrderService;
import org.jbit.service.impl.EvaluateServiceImpl;
import org.jbit.service.impl.OrderServiceImpl;
import org.jbit.utils.JdbcUtil;
import org.jbit.vo.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.jbit.utils.StringUtil.toStar;

/**
 * @author jzt
 * @description
 * @date 2020/12/4
 */
@WebServlet("/evaluate")
public class EvaluateController extends BaseController{

    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);

    /**
     * 注入Service
     */
    EvaluateService evaluateService=new EvaluateServiceImpl();
    OrderService orderService=new OrderServiceImpl();
    /**
     * 数据列表
     *
     * @param req
     * @param res
     */
    public void putevaluate(HttpServletRequest req, HttpServletResponse res) throws Exception{

        //获取评价信息
        String orderId=req.getParameter("orderId");
        String evaluateContent=toStar(req.getParameter("evaluateContent"));
        Integer evaluateLevel=Integer.parseInt(req.getParameter("evaluateLevel"));

        //查询订单表和生成评价表
        Order order=orderService.selectById(orderId);
        Evaluate evaluate=new Evaluate(order,evaluateContent,evaluateLevel);
        //存放处理并记录结果
        ResultVo resultVo=null;
        try{
            if(evaluateService.countById(orderId)){
                evaluateService.save(evaluate);
            }else{
                evaluateService.create(evaluate);
            }
            resultVo = new ResultVo(0, "成功");
        }catch (Exception e){
            resultVo = new ResultVo(500, "未知服务错误");
        }
        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);


    }

    public void getevaluates(HttpServletRequest req, HttpServletResponse res) throws Exception{
        String roomId=req.getParameter("roomId");
        List<Evaluate> evaluates = new ArrayList<Evaluate>();


        ResultVo resultVo=null;

        try{
            evaluates=evaluateService.selectByRoomId(roomId);
            resultVo = new ResultVo<>(evaluates);
        }catch (Exception e){
            e.printStackTrace();
            resultVo = new ResultVo(500, "未知服务错误");
        }

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }

}
