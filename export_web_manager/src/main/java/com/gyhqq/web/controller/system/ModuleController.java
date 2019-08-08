package com.gyhqq.web.controller.system;

import com.gyhqq.domain.system.Module;
import com.gyhqq.service.system.ModuleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/system/module")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer size) {
        PageInfo pageInfo = moduleService.findPageByHelper(page, size);
        model.addAttribute("page", pageInfo);
        return "/system/module/module-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        List<Module> list = moduleService.findAll();
        model.addAttribute("menus", list);
        return "/system/module/module-add";
    }

    @RequestMapping("/edit")
    public String edit(Module module) {
        if (StringUtils.isEmpty(module.getId())) {
            moduleService.save(module);
        } else {
            //更新
            moduleService.update(module);
        }
        return "redirect:/system/module/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Model model, String id) {
        Module module = moduleService.findById(id);
        model.addAttribute("module", module);
        List<Module> list = moduleService.findAll();
        model.addAttribute("menus", list);
        return "/system/module/module-update";
    }

    @RequestMapping("/delete")
    public String delete(String id) {
        moduleService.delete(id);
        return "redirect:/system/module/list.do";
    }

}
