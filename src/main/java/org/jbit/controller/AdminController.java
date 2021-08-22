package org.jbit.controller;

import com.alibaba.fastjson.JSON;
import org.jbit.entity.User;
import org.jbit.vo.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author yh
 * @version 1.0,2020-12-05
 */
@WebServlet("/admin")
public class AdminController extends BaseController{

    /**
     * 获取登录者个人信息
     * @param req
     * @param resp
     * @throws Exception
     */
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
