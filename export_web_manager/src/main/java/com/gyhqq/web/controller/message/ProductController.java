package com.gyhqq.web.controller.message;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.domain.cargo.Factory;
import com.gyhqq.domain.cargo.FactoryExample;
import com.gyhqq.domain.message.Product;
import com.gyhqq.domain.message.ProductExample;
import com.gyhqq.domain.system.User;
import com.gyhqq.service.cargo.FactoryService;
import com.gyhqq.service.message.ProductService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/message/product")
public class ProductController extends BaseController {

    @Reference
    private ProductService productService;

    @Reference
    private FactoryService factoryService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer size) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(getLoginCompanyId());
        PageInfo info = productService.findAll(example, page, size);
        request.setAttribute("page", info);
        return "message/product/product-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        //工厂相关
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria fCriteria = factoryExample.createCriteria();
        fCriteria.andCtypeEqualTo("货物");
        List<Factory> factories = factoryService.selectByExample(factoryExample);
        request.setAttribute("factoryList", factories);
        return "message/product/product-add";
    }

    @RequestMapping("/edit")
    public String edit(Product product) {
        product.setCompanyId(getLoginCompanyId());
        product.setCompanyName(getLoginCompanyName());
        User user = getLoginUser();
        //判断if是否为空
        if (StringUtils.isEmpty(product.getId())) {
            //为空 保存
            product.setCreateBy(user.getUserName());
            product.setCreateDept(user.getDeptName());
            productService.save(product);
        } else {
            //不为空 更新
            productService.update(product);
        }
        return "redirect:/message/product/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id) {
        Product product = productService.findById(id);
        //工厂相关
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria fCriteria = factoryExample.createCriteria();
        fCriteria.andCtypeEqualTo("货物");
        List<Factory> factories = factoryService.selectByExample(factoryExample);
        request.setAttribute("factoryList", factories);
        request.setAttribute("product", product);
        return "message/product/product-update";
    }

    @RequestMapping("/delete")
    public String delete(String id) {
        productService.delete(id);
        return "redirect:/message/product/list.do";
    }

}
