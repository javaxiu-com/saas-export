package com.gyhqq.web.controller.system;

import com.gyhqq.domain.system.Dept;
import com.gyhqq.service.system.DeptService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {
    @Autowired
    private DeptService deptService;

    /**
     * 部门列表
     */
    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer size) {
        PageInfo pageInfo = deptService.findPageByHelper(page, size, getLoginCompanyId());
        model.addAttribute("page", pageInfo);
        return "/system/dept/dept-list";
    }

    /**
     * 进入保存页面
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        List<Dept> list = deptService.findAll(getLoginCompanyId());
        model.addAttribute("deptList", list);
        return "/system/dept/dept-add";

    }

    /**
     * 保存和更新
     */
    @RequestMapping("/edit")
    public String edit(Dept dept) {
        dept.setCompanyId(getLoginCompanyId());
        dept.setCompanyName(getLoginCompanyName());
        if (StringUtils.isEmpty(dept.getId())) {
            deptService.save(dept);
        } else {
            deptService.update(dept);
        }
        return "redirect:/system/dept/list.do";
    }

    /**
     * 进入更新页面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        //根据id查询部门信息
        Dept dept = deptService.findById(id);
        model.addAttribute("dept", dept);
        //公司所有部门列表回显
        List<Dept> list = deptService.findAll(getLoginCompanyId());
        model.addAttribute("deptList", list);
        return "/system/dept/dept-update";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(String id) {
        deptService.delete(id);
        return "redirect:/system/dept/list.do";
    }
}
