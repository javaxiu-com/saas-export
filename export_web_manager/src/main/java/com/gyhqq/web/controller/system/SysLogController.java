package com.gyhqq.web.controller.system;

import com.gyhqq.service.system.SysLogService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/system/log")
public class SysLogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer size,
                       Model model) {

        PageInfo pageInfo = sysLogService.findAll(page, size, getLoginCompanyId());
        model.addAttribute("page", pageInfo);
        return "/system/log/log-list";
    }

}
