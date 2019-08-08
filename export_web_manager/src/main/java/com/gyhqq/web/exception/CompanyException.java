package com.gyhqq.web.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CompanyException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ex", ex);
        modelAndView.setViewName("error/error");
        return modelAndView;
        /*if (ex instanceof UnauthorizedException){
            modelAndView.setViewName("forward:/unauthorized.jsp");
            return modelAndView;
        }else{
            modelAndView.addObject("ex",ex);
            modelAndView.setViewName("error/error");
            return modelAndView;
        }*/

    }
}
