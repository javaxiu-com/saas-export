package com.gyhqq.web.controller.basics;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.domain.cargo.Factory;
import com.gyhqq.domain.cargo.FactoryExample;
import com.gyhqq.domain.message.ProductExample;
import com.gyhqq.service.cargo.FactoryService;
import com.gyhqq.service.message.ProductService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/basics/factory")
public class FactoryController extends BaseController {
    @Reference
    private FactoryService factoryService;
    @Reference
    private ProductService ProductService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer size,
                       Model model) {
        FactoryExample example = new FactoryExample();
        FactoryExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(getLoginCompanyId());
        PageInfo info = factoryService.findAll(example, page, size);
        model.addAttribute("page", info);
        return "/cargo/factory/factory-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/cargo/factory/factory-add";
    }

    //查看厂家的货物
    @RequestMapping("/toView")
    public String toView(String id,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "5") Integer size) {
        Factory factory = factoryService.selectByPrimaryKey(id);
        request.setAttribute("o", factory);
        ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andFactoryIdEqualTo(id);
        PageInfo all = ProductService.findAll(productExample, page, size);
        request.setAttribute("page", all);
        return "cargo/factory/factoryproduct-list";
    }

    @RequestMapping("/edit")
    public String edit(Factory factory) {

        if (StringUtils.isEmpty(factory.getId())) {
            factory.setCompanyId(getLoginCompanyId());
            factory.setCompanyName(getLoginCompanyName());
            factory.setCreateBy(getLoginUser().getId());
            factory.setCreateDept(getLoginUser().getDeptId());
            factoryService.save(factory);
        } else {
            factoryService.update(factory);
        }
        return "redirect:/basics/factory/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        Factory factory = factoryService.selectByPrimaryKey(id);
        model.addAttribute("factory", factory);
        return "/cargo/factory/factory-update";
    }

    @RequestMapping("/delete")
    public String delete(String id) {
        factoryService.deleteByPrimaryKey(id);
        return "redirect:/basics/factory/list.do";
    }
}
