package com.gyhqq.web.controller;

import com.gyhqq.common.utils.Encrypt;
import com.gyhqq.common.utils.HttpUtils;
import com.gyhqq.domain.system.Module;
import com.gyhqq.domain.system.User;
import com.gyhqq.service.system.ModuleService;
import com.gyhqq.service.system.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModuleService moduleService;

    /**
     * 登录方法
     *  i.参数 : 邮箱和密码
     *  ii.业务
     *      1.根据邮箱查询用户 : user对象(包含数据库中的密码)
     *      2.判断用户是否存在
     *      3.判断登录密码和数据库中的密码是否一致
     *          3.1 : 用户存在且密码一致 , 将用户数据保存到session域,跳转到主页面
     *          3.2 : 用户不存在或者密码不一致: 跳转到登录页面重新登录
     */
	/*@RequestMapping("/login")
	public String login(String email,String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            request.setAttribute("error","用户名或密码为空");
            return "forward:/login.jsp";
        }

        //根据邮箱查询用户
        User user = userService.findByEamil(email);
        //对用户登录的密码进行加密处理  md5
        password = Encrypt.md5(password,email);

        //判断
        if(user != null && password.equals(user.getPassword())) {
            //用户存在且密码一致 , 将用户数据保存到session域,跳转到主页面
            session.setAttribute("loginUser",user);
            //用户登录之后调用模块的service查询所有的课操作菜单,来构建动态菜单
            List<Module> modules = moduleService.findByUserId(user.getId());
            session.setAttribute("modules",modules);
            return "home/main";
        }else{
            //用户不存在或者密码不一致: 跳转到登录页面重新登录
            request.setAttribute("error","用户名或密码不一致");
            return "forward:/login.jsp";
        }
    }*/

    @RequestMapping("/firstLogin")
    public String firstLogin(String email, String password, String openId) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(email, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("loginUser", user);
            List<Module> modules = moduleService.findByUserId(user.getId());
            session.setAttribute("modules", modules);
            userService.AddOpenId(openId, email);
            return "home/main";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "用户名或密码错误");
            return "/weiXinLogin";
        }
    }

    @RequestMapping("/weiXinLogin")
    public String weiXinLogin(String code) {
        String appId = "wx3bdb1192c22883f3";
        String secret = "db9d6b88821df403e5ff11742e799105";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";
        Map<String, Object> map = HttpUtils.sendGet(url);
        String openId = map.get("openid").toString();
        User user = userService.findByOpenId(openId);
        if (user == null) {
            User user1 = new User();
            user1.setOpenId(openId);
            request.setAttribute("user", user1);
            return "/weiXinLogin";
        } else {
            return "redirect:/login.do?email=" + user.getEmail() + "&password=" + user.getPassword() + "&openId=" + user.getOpenId().toString();
        }
    }

    /**
     * 基于shiro的登录
     */
    @RequestMapping("/login")
    public String login(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            request.setAttribute("error", "用户名或密码为空");
            return "forward:/login.jsp";
        }
        //构造token 获取subject对象 调用subject的login方法进行登录
        if (request.getParameter("openId") == null) {
            try {
                //1.构造token:shiro将用户的用户名和密码进行了封装
                UsernamePasswordToken token = new UsernamePasswordToken(email, password);
                //2.获取subject对象
                Subject subject = SecurityUtils.getSubject();
                //3.调用subject的login方法进行登录
                subject.login(token);
                //4.正常执行,登录成功
                //4.1 将登录用户保存到session
                User user = (User) subject.getPrincipal();  //Principal :shiro中安全数据
                session.setAttribute("loginUser", user);
                //4.2 构造菜单数据保存到session
                List<Module> modules = moduleService.findByUserId(user.getId());
                session.setAttribute("modules", modules);
                //4.3 跳转到主页
                return "home/main";
            } catch (Exception e) {
                e.printStackTrace();
                //5.抛出异常,登录失败
                request.setAttribute("error", "用户名或密码错误");
                return "forward:/login.jsp";
            }
        } else {
            String openId = request.getParameter("openId");
            String enterEmail = request.getParameter("email");
            String enterPassword = request.getParameter("password");
            UsernamePasswordToken token = new UsernamePasswordToken(enterEmail, enterPassword, openId);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            //获取User对象
            User user = (User) subject.getPrincipal();
            session.setAttribute("loginUser", user);
            List<Module> modules = moduleService.findByUserId(user.getId());
            session.setAttribute("modules", modules);
            return "home/main";
        }
    }

    //退出
    @RequestMapping(value = "/logout", name = "用户登出")
    public String logout() {
        //为了清空shiro中的所有内存安全数据
        //使用shiro进行登出
        SecurityUtils.getSubject().logout();   //登出
        return "forward:login.jsp";
    }

    @RequestMapping("/home")
    public String home() {
        return "home/home";
    }

    @RequestMapping("/changePassword")
    public @ResponseBody
    int changePassword(String oldPassword, String newPassword) {
        //System.out.println(oldPassword+":"+newPassword);
        User user = getLoginUser();
        oldPassword = Encrypt.md5(oldPassword, user.getEmail());
        if (oldPassword.equals(user.getPassword())) {
            //1代表密码正确,然后更新密码
            user.setPassword(newPassword);
            userService.update(user);
            return 1;
        } else {
            return 0;
        }

    }

}
