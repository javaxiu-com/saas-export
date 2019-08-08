package com.gyhqq.web.controller.system;

import com.gyhqq.domain.system.Module;
import com.gyhqq.domain.system.Role;
import com.gyhqq.service.system.ModuleService;
import com.gyhqq.service.system.RoleService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModuleService moduleService;

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer size, Model model) {
        PageInfo pageInfo = roleService.findPageByHelper(page, size, getLoginCompanyId());
        model.addAttribute("page", pageInfo);
        return "/system/role/role-list";
    }

    /**
     * 进入添加界面
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/system/role/role-add";
    }

    /**
     * 保存和更新
     */
    @RequestMapping("/edit")
    public String edit(Role role) {
        role.setCompanyId(getLoginCompanyId());
        role.setCompanyName(getLoginCompanyName());
        if (StringUtils.isEmpty(role.getId())) {
            roleService.save(role);
        } else {
            //更新
            roleService.update(role);
        }
        return "redirect:/system/role/list.do";
    }

    /**
     * 进入更新页面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "/system/role/role-update";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(String id) {
        roleService.delete(id);
        return "redirect:/system/role/list.do";
    }

    @RequestMapping("/roleModule")
    public String roleModule(String roleid, Model model) {
        Role role = roleService.findById(roleid);
        model.addAttribute("role", role);
        return "/system/role/role-module";
    }

    //{ id:2, pId:0, name:"随意勾选 2", checked:true,
    @RequestMapping("/getZtreeDatas")
    public @ResponseBody
    List getZtreeDatas(String roleId) {
        List list = new ArrayList();
        List<Module> modules = moduleService.findAll();
        List<Module> roleModules = moduleService.findByRoleId(roleId);
        for (Module module : modules) {//遍历的是所有,如果遍历的是某个角色的权限那么就要反过来包含
            Map map = new HashMap();
            map.put("id", module.getId());
            map.put("pId", module.getParentId());
            map.put("name", module.getName());
            if (roleModules.contains(module)) {
                map.put("checked", "true");
            }
            list.add(map);
        }
        return list;
    }

    @RequestMapping("/updateRoleModule")
    public String updateRoleModule(String roleid, String moduleIds) {
        roleService.updateRoleModule(roleid, moduleIds);
        return "redirect:/system/role/list.do";
    }

}
