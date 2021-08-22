package org.jbit.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    private String excludeUrl;

    /**
     * 过滤处理
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        //获取url参数 action=login&xxxx=dd&ddd
        String action=req.getQueryString();
        String urlParams=req.getRequestURI()+"?"+action;
        /*
         * 如果过滤器为login，不拦截请求
         * 否则：
         *   如果用户已登录，不拦截请求
         *   否则，输出错误信息
         */
        if(urlParams!=null && urlParams.contains(excludeUrl)){
            //进入下一个过滤链
            chain.doFilter(request, response);
        }else{
            //是否登录
            HttpSession session=req.getSession();
            if(session.getAttribute("login")!=null){
                //进入下一个过滤链
                chain.doFilter(request, response);
            }else{

                resp.sendRedirect("login.html");
            }
        }
    }
    /**
     * 获取初始化参数
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取初始化参数
        excludeUrl=filterConfig.getInitParameter("excludeUrl");
    }

    @Override
    public void destroy() {
    }
}
