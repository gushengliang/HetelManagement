package org.jbit.controller;

import com.alibaba.fastjson.JSON;
import org.jbit.dto.CompensateQueryDto;
import org.jbit.entity.Compensate;
import org.jbit.service.CompensateService;
import org.jbit.service.impl.CompensateServiceImpl;
import org.jbit.utils.PageUtil;
import org.jbit.utils.StringUtil;
import org.jbit.vo.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/compensate")
public class CompensateController extends BaseController {
    /**
     * 注入Service
     */
    CompensateService compensateService = new CompensateServiceImpl();

    /**
     * 数据列表
     * @param req
     * @param res
     */
    private void list(HttpServletRequest req, HttpServletResponse res) throws Exception{
        //接收请求
        String compensateId = req.getParameter("compensateId");
        String compensateName = req.getParameter("compensateName");
        Double compensatePrice = StringUtil.toDouble(StringUtil.toNull(req.getParameter("compensatePrice")));
        Integer currentPage = Integer.parseInt(req.getParameter("currentPage"));
        Integer pageSize = Integer.parseInt(req.getParameter("pageSize"));

        //封装对象
        CompensateQueryDto compensateQueryDto = new CompensateQueryDto(
                compensateId,
                compensateName,
                compensatePrice,
                currentPage,
                pageSize
        );

        //处理数据
        PageUtil pageUtil = null;
        ResultVo resultVo = null;
        try {
            //调用Service
            pageUtil = compensateService.pageList(compensateQueryDto);
            resultVo = new ResultVo<PageUtil>(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(500, "未知服务错误");
        }

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }

    private void create(HttpServletRequest req, HttpServletResponse res) throws Exception{
        //接收请求
        String compensateId = req.getParameter("compensateId");
        String compensateName = req.getParameter("compensateName");
        Double compensatePrice = Double.parseDouble(req.getParameter("compensatePrice"));

        //封装对象
        Compensate compensate = new Compensate(compensateId, compensateName, compensatePrice);
        //处理数据
        int rows = compensateService.create(compensate);
        ResultVo resultVo = new ResultVo(rows);

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }

    private void save(HttpServletRequest req, HttpServletResponse res) throws Exception{
        //接收请求
        String compensateId = req.getParameter("compensateId");
        String compensateName = req.getParameter("compensateName");
        Double compensatePrice = Double.parseDouble(req.getParameter("compensatePrice"));

        //封装对象
        Compensate compensate = new Compensate(compensateId, compensateName, compensatePrice);
        //处理数据
        int rows = compensateService.save(compensate);
        ResultVo resultVo = new ResultVo(rows);

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }
}
