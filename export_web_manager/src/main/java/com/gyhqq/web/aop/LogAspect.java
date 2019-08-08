package com.gyhqq.web.aop;

import com.gyhqq.domain.system.SysLog;
import com.gyhqq.domain.system.User;
import com.gyhqq.service.system.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAspect {
    //com.gyhqq.web.controller.company.CompanyController.list()
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    /**
     * private String userName;
     * private String ip;
     * private Date time;
     * private String method;
     * private String action;
     * private String companyId;
     * private String companyName;
     * 这个环绕通知虽然是在切点方法之前执行,但是不能使用@Before,因为method与action参数只能用
     * ProceedJoinPoint的对象才能获得,所以要用环绕通知
     *
     * @param pjp
     * @return
     */
    @Around("execution(* com.gyhqq.web.controller.*.*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method method = ms.getMethod();
        String methodName = method.getName();
        String methodComment = "";
        if (method.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping mapping = method.getAnnotation(RequestMapping.class);
            methodComment = mapping.name();
        }
        SysLog log = new SysLog();
        Object object = session.getAttribute("loginUser");
        if (object != null) {
            User user = (User) object;
            log.setUserName(user.getUserName());
            log.setCompanyId(user.getCompanyId());
            log.setCompanyName(user.getCompanyName());
        }
        log.setTime(new Date());
        log.setIp(request.getLocalAddr());
        log.setMethod(methodName);
        log.setAction(methodComment);

        sysLogService.save(log);
        Object proceed = pjp.proceed();
        return proceed;
    }

}
