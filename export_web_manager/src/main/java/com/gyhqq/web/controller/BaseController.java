package com.gyhqq.web.controller;

import com.gyhqq.domain.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    /**
     * 当发送一次请求的时候,springmvc自动的从当前线程获取到request对象并注入<ThredLocal></ThredLocal>
     * 执行多次请求
     */
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpSession session;
    @Autowired
    protected HttpServletResponse response;

    /**
     * 获取当前登录用户的企业id
     */
    protected String getLoginCompanyId() {
        Object obj = session.getAttribute("loginUser");
        if (obj != null) {
            User user = (User) obj;
            return user.getCompanyId();
        }
        return "";
    }

    /**
     * 获取当前登录用户的企业名称
     */
    protected String getLoginCompanyName() {
        Object obj = session.getAttribute("loginUser");
        if (obj != null) {
            User user = (User) obj;
            return user.getCompanyName();
        }
        return "";
    }

    /**
     * 获取当前登录用户
     */
    protected User getLoginUser() {
        Object obj = session.getAttribute("loginUser");
        if (obj != null) {
            User user = (User) obj;
            return user;
        }
        return null;
    }

}
