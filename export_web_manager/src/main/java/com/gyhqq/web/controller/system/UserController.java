package com.gyhqq.web.controller.system;

import com.gyhqq.domain.system.Dept;
import com.gyhqq.domain.system.Role;
import com.gyhqq.domain.system.User;
import com.gyhqq.service.system.DeptService;
import com.gyhqq.service.system.RoleService;
import com.gyhqq.service.system.UserService;
import com.gyhqq.web.activemq.EmailProducer;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmailProducer emailProducer;

    /**
     * 用户列表
     */
    @RequestMapping(value = "/list", name = "用户分页")
    public String list(Model model,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer size) {
        PageInfo pageInfo = userService.findPageByHelper(page, size, getLoginCompanyId());
        model.addAttribute("page", pageInfo);
        return "/system/user/user-list";
    }

    /**
     * 进入添加页面
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        //查询该用户所有部门:数据回显
        List<Dept> list = deptService.findAll(getLoginCompanyId());
        model.addAttribute("deptList", list);
        return "/system/user/user-add";
    }

    /**
     * 保存和更新
     */
    @RequestMapping(value = "/edit", name = "添加用户")
    public String edit(User user) {
        //保存前先设置公司id和名称
        user.setCompanyId(getLoginCompanyId());
        user.setCompanyName(getLoginCompanyName());
        String password = user.getPassword();
        if (StringUtils.isEmpty(user.getId())) {
            userService.save(user);
            //保存用户信息后给用户发邮件提醒
            try {
                Map<String, String> map = new HashMap<>();
                map.put("receive", user.getEmail());
                map.put("subject", "欢迎使用SaaS-Export平台");
                map.put("content", "尊敬的用户您好!欢迎使用SaaS-Export平台,登录地址为:http://localhost:8081," +
                        "您的账号为" + user.getEmail() + ",您的密码为" + password);
                emailProducer.sendMessage(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //更新
            userService.update(user);
        }
        return "redirect:/system/user/list.do";
    }

    /**
     * 进入更新页面
     */
    @RequestMapping(value = "/toUpdate", name = "更新用户")
    public String toUpdate(Model model, String id) {
        //根据id查询用户信息
        User user = userService.findById(id);
        model.addAttribute("user", user);
        //根据登录企业id查询所有部门,回显数据
        List<Dept> list = deptService.findAll(getLoginCompanyId());
        model.addAttribute("deptList", list);
        return "/system/user/user-update";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(String id) {
        userService.delete(id);
        return "redirect:/system/user/list.do";
    }

    /**
     * 给用户添加角色
     */
    @RequestMapping(value = "/roleList", name = "给用户添加角色")
    public String roleList(String id, Model model) {
        //1.根据用户id查找该用户
        User user = userService.findById(id);
        model.addAttribute("user", user);
        //2.根据公司Id查找所有的角色
        List roles = roleService.findAll(getLoginCompanyId());
        model.addAttribute("roleList", roles);
        //3.根据用户Id查找该用户的所以角色
        List<Role> userRoles = roleService.findByUserId(id);
        //4.循环遍历用户的角色集合,把其id拼接成字符串
        StringBuffer stringBuffer = new StringBuffer();
        for (Role userRole : userRoles) {
            stringBuffer.append(userRole.getId());
        }
        model.addAttribute("userRoleStr", stringBuffer.toString());
        return "/system/user/user-role";
    }

    @RequestMapping("/changeRole")
    public String changeRole(String userid, String[] roleIds) {
        //1.根据用户id删除中间表数据
        //2.根据roleIds保存
        userService.changeRole(userid, roleIds);
        return "redirect:/system/user/list.do";
    }

}
