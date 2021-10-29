package com.imooc.mall.filter;

import com.imooc.mall.common.Constant;
import com.imooc.mall.model.pojo.User;
import com.imooc.mall.service.CategoryService;
import com.imooc.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 描述：用户校验过滤器
 */
public class UserFilter implements Filter {

    public static User currentUser;  //为啥定义成静态变量 ?

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        currentUser = (User) session.getAttribute(Constant.IMOOC_MALL_USER);
        if (currentUser == null) {
            PrintWriter out = new HttpServletResponseWrapper( (HttpServletResponse) servletResponse).getWriter();
            out.write("{\n" +
                    "    \"status\": 10007,\n" +
                    "    \"msg\": \"NEED_LOGIN\",\n" +
                    "    \"data\": null\n" +
                    "}");
            out.flush();
            out.close();
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse); //通过校验，逻辑继续走下去，可以进行购物车操作
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
