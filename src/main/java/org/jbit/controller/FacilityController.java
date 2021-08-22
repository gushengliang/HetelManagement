package org.jbit.controller;

import com.alibaba.fastjson.JSON;
import org.jbit.dto.FacilityQueryDto;
import org.jbit.entity.Facility;
import org.jbit.service.FacilityService;
import org.jbit.service.impl.FacilityServiceImpl;
import org.jbit.utils.PageUtil;
import org.jbit.utils.StringUtil;
import org.jbit.vo.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/facility")
public class FacilityController extends BaseController {
    /**
     * 注入Service
     */
    FacilityService facilityService = new FacilityServiceImpl();

    /**
     * 数据列表
     * @param req
     * @param res
     */
    private void list(HttpServletRequest req, HttpServletResponse res) throws Exception{
        //接收请求
        String facilityId = req.getParameter("facilityId");
        String facilityName = req.getParameter("facilityName");
        Double facilityPrice = StringUtil.toDouble(StringUtil.toNull(req.getParameter("facilityPrice")));
        Integer currentPage = Integer.parseInt(req.getParameter("currentPage"));
        Integer pageSize = Integer.parseInt(req.getParameter("pageSize"));

        //封装对象
        FacilityQueryDto facilityQueryDto = new FacilityQueryDto(
                facilityId,
                facilityName,
                facilityPrice,
                currentPage,
                pageSize
        );

        //处理数据
        PageUtil pageUtil = null;
        ResultVo resultVo = null;
        try {
            //调用Service
            pageUtil = facilityService.pageList(facilityQueryDto);
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

    private void create(HttpServletRequest req, HttpServletResponse res) throws Exception{
        //接收请求
        String facilityId = req.getParameter("facilityId");
        String facilityName = req.getParameter("facilityName");
        Double facilityPrice = Double.parseDouble(req.getParameter("facilityPrice"));

        //封装对象
        Facility facility = new Facility(facilityId, facilityName, facilityPrice);
        //处理数据
        int rows = facilityService.create(facility);
        ResultVo resultVo = new ResultVo(rows);

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }

    private void save(HttpServletRequest req, HttpServletResponse res) throws Exception{
        //接收请求
        String facilityId = req.getParameter("facilityId");
        String facilityName = req.getParameter("facilityName");
        Double facilityPrice = Double.parseDouble(req.getParameter("facilityPrice"));

        //封装对象
        Facility facility = new Facility(facilityId, facilityName, facilityPrice);
        //处理数据
        int rows = facilityService.save(facility);
        ResultVo resultVo = new ResultVo(rows);

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }
    private void room_list(HttpServletRequest req, HttpServletResponse res) throws Exception{
        //接收请求
        String roomId=req.getParameter("roomId");
        //设备列表
        List<Facility> facilities=new ArrayList<> ();
        facilities=facilityService.selectbyroomid(roomId);
        ResultVo resultVo=null;
        try {
            //调用Service
            resultVo = new ResultVo<>(facilities);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(500, "未知服务错误");
        }

        String json = JSON.toJSONString(resultVo);
        // 响应结果
        PrintWriter out = res.getWriter();
        out.print(json);
    }
}
