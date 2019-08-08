package com.gyhqq.web.controller.company;

import com.gyhqq.domain.company.Company;
import com.gyhqq.service.company.CompanyService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {
    @Autowired
    private CompanyService companyService;

    /**
     * 查询所有公司列表
     */
    @RequestMapping("/list")
    public String list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model) {
        /*PageResult pr = companyService.findAll(page,size);
        model.addAttribute("page",pr);*/
        PageInfo pr = companyService.findPageByHelp(page, size);
        model.addAttribute("page", pr);
        return "company/company-list";
    }

    /**
     * 重定向必须要加/
     */
    @RequestMapping("/date")
    public String converterDate(Date time) {
        System.out.println(time);
        return "redirect:/company/list.do";
    }

    /**
     * 进入新增页面
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "company/company-add";
    }

    /**
     * 保存和更新
     */
    @RequestMapping("/edit")
    public String edit(Company company) {
        if (StringUtils.isEmpty(company.getId())) {
            companyService.save(company);
        } else {
            companyService.updateCompany(company);
        }
        return "redirect:/company/list.do";
    }

    /**
     * 进入修改界面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        Company company = companyService.findById(id);
        model.addAttribute("company", company);
        return "company/company-update";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(String id) {
        companyService.delete(id);
        return "redirect:/company/list.do";
    }

}
