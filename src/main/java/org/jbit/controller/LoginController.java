package org.jbit.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;

import org.jbit.entity.Staff;
import org.jbit.entity.User;
import org.jbit.service.StaffService;
import org.jbit.service.UserService;
import org.jbit.service.impl.StaffServiceImpl;
import org.jbit.service.impl.UserServiceImpl;
import org.jbit.utils.StringUtil;
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
@WebServlet("/index")
public class LoginController extends BaseController {
	private UserService userService=new UserServiceImpl();
	/**
	 * 登录
	 * @param req
	 * @param resp
	 * @throws Exception 
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取参数
		Integer type = StringUtil.toInt(req.getParameter("type"));
		String username = req.getParameter("userName");
		String password = req.getParameter("password");
		//密码加密
		String token = DigestUtils.md5Hex(password);

		User user = null;

		//按用户类型和用户名查询用户信息
		if(type == 2) {
			user = userService.findCustomerByUserName(username);
		}
		if(type == 3) {
			StaffService staffService = new StaffServiceImpl();
			Staff staff = staffService.selectById(username);
			if(staff.getOnJob() == 1) {
				user = userService.findStaffByUserName(username);
			}
		}
		if(type == 1) {
			user = userService.findAdministratorByUserName(username);
		}

		//按用户名查询用户信息
		//User user=userService.findByUsername(username);


		/*如果用户存在，比较密码
		如果密码相同，登录成功，创建session
		否则，抛出异常*/

		ResultVo resultVo = null;
		if(user != null){
			if(user.getPassword().equals(token)){
				//将密码设置为加密前的密码，方便修改密码时的验证
				user.setPassword(password);
				//创建session
				HttpSession session = req.getSession();
				//session失效时间10分钟（默认为30分钟）
				session.setMaxInactiveInterval(10*60);
				//创建登录标记
				session.setAttribute("login", user);
				//用户身份验证通过，封装到resultVo
				resultVo = new ResultVo<>(user);
			}else{
				resultVo = new ResultVo(402, "密码错误");
			}
		}else{
			resultVo = new ResultVo(401, "用户不存在或已禁用");
		}
		
		PrintWriter out=resp.getWriter();
		String json=JSON.toJSONString(resultVo);
		out.print(json);
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//创建session
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("login");
		ResultVo resultVo = null;

		if(user != null) {
			session.removeAttribute("login");
			resultVo = new ResultVo(0, "注销成功");
		} else {
			resultVo = new ResultVo(440, "注销失败");
		}

		PrintWriter out=resp.getWriter();
		String json=JSON.toJSONString(resultVo);
		out.print(json);
	}

	private void changePassword(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userId = req.getParameter("userId");
		//String currentPassword = req.getParameter("currentPassword");
		String newPassword = req.getParameter("newPassword");
		Integer type = StringUtil.toInt(req.getParameter("type"));

		//String currentToken = DigestUtils.md5Hex(currentPassword);
		String newToken = DigestUtils.md5Hex(newPassword);

		User user = new User();
		user.setId(userId);
		user.setPassword(newToken);

		HttpSession session = req.getSession();

		int rows = 0;

		//按用户类型和用户名查询用户信息
		if(type == 2) {
			rows = userService.changeCustomerPassword(user);
		}
		if(type == 3) {
			rows = userService.changeStaffPassword(user);
		}
		if(type == 1) {
			rows = userService.changeAdministratorPassword(user);
		}

		ResultVo resultVo = null;
		if(rows > 0) {
			session.removeAttribute("login");
			resultVo = new ResultVo(0, "密码修改成功");
		} else {
			resultVo = new ResultVo(450, "密码修改失败");
		}

		PrintWriter out=resp.getWriter();
		String json=JSON.toJSONString(resultVo);
		out.print(json);
	}
}
