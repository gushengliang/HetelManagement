package org.jbit.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.jbit.dto.StaffQueryDto;
import org.jbit.entity.Staff;
import org.jbit.entity.User;
import org.jbit.service.StaffService;
import org.jbit.service.impl.StaffServiceImpl;
import org.jbit.utils.PageUtil;
import org.jbit.utils.StringUtil;
import org.jbit.vo.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 员工控制器
 * @author yh
 * @version 1.0,2020-11-23
 *
 */
@WebServlet("/staff")
public class StaffController extends BaseController{

    /**
     * 注入Service
     */
    StaffService staffService = new StaffServiceImpl();

    /**
     * 数据列表
     * @param req
     * @param res
     */
    private void list(HttpServletRequest req, HttpServletResponse res) throws Exception{
        //接受请求
        String staffId =req.getParameter("staffId");
        String staffName =req.getParameter("staffName");
        String staffPassword =req.getParameter("staffPassword");
        String staffPosition =req.getParameter("staffPosition");
        Integer onJob = StringUtil.toInt(req.getParameter("onJob"));
        Integer currentPage = StringUtil.toInt(req.getParameter("currentPage"));
        Integer pageSize = StringUtil.toInt(req.getParameter("pageSize"));

        //封装对象
        StaffQueryDto staffQueryDto = new StaffQueryDto(
                staffId,
                staffName,
                staffPassword,
                staffPosition,
                onJob,
                currentPage,
                pageSize
        );

        //处理数据
        PageUtil pageUtil = null;
        ResultVo resultVo = null;
        try {
            //调用Service
            pageUtil = staffService.pageList(staffQueryDto);
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

    private void dismiss(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //接受请求
        String staffId =req.getParameter("staffId");

        staffService.dismissById(staffId);
        int rows = 0;
        ResultVo resultVo = null;

        try {
            //调用Service
            rows = staffService.dismissById(staffId);
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

    private void create(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String staffId = req.getParameter("staffId");
        String staffName =req.getParameter("staffName");
        String staffPosition =req.getParameter("staffPosition");

        String staffPassword = DigestUtils.md5Hex("123456");

        Staff staff = new Staff(staffId, staffName, staffPassword, staffPosition);

        int rows = staffService.create(staff);

        ResultVo resultVo = new ResultVo(rows);

        String json = JSON.toJSONString(resultVo);
        PrintWriter out = res.getWriter();
        out.print(json);
    }

    private void save(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String staffId = req.getParameter("staffId");
        String staffName =req.getParameter("staffName");
        String staffPosition =req.getParameter("staffPosition");

        Staff staff = staffService.selectById(staffId);

        //Staff staff = new Staff();
        staff.setStaffId(staffId);
        staff.setStaffName(staffName);
        staff.setStaffPosition(staffPosition);

        int rows = staffService.save(staff);

        ResultVo resultVo = new ResultVo(rows);

        String json = JSON.toJSONString(resultVo);
        PrintWriter out = res.getWriter();
        out.print(json);
    }

    private void dismissMany(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String[] multipleSelections = req.getParameterValues("multipleSelection");

        int rows = 0;
        ResultVo resultVo = null;

        try {
            //调用Service
            for(String staffId : multipleSelections) {
                rows += staffService.dismissById(staffId);
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

    private void isExist(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //获取参数
        String staffId = req.getParameter("staffId");
        //封装对象
        Staff staff = new Staff();
        staff.setStaffId(staffId);

        //保存数据
        ResultVo resultVo = null;
        //将对象封装到resultVo
        try {
            if(staffService.isExist(staff) == 0) {
                resultVo= new ResultVo<>(staff);
            }
            else if (staffService.isExist(staff) > 0){
                resultVo = new ResultVo(501, "该员工已经存在");
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

    private void getLoginInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("login");
        //System.out.println(session.getAttribute("login"));
        ResultVo resultVo = null;
        if(user != null) {
            resultVo = new ResultVo<User>(user);
        } else {
            resultVo = new ResultVo(403, "用户身份信息已过期");
        }

        PrintWriter out=resp.getWriter();
        String json= JSON.toJSONString(resultVo);
        out.print(json);
    }
}
