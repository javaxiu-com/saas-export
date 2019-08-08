package com.gyhqq.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.common.utils.FileUploadUtils;
import com.gyhqq.domain.cargo.ExtCproduct;
import com.gyhqq.domain.cargo.ExtCproductExample;
import com.gyhqq.domain.cargo.Factory;
import com.gyhqq.domain.cargo.FactoryExample;
import com.gyhqq.service.cargo.ExtCproductService;
import com.gyhqq.service.cargo.FactoryService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cargo/extCproduct")
public class ExtCproductController extends BaseController {

    @Reference
    private ExtCproductService extCproductService;
    @Reference
    private FactoryService factoryService;

    @RequestMapping("/list")
    public String list(Model model, String contractId, String contractProductId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer size) {

        ExtCproductExample extCproductExample = new ExtCproductExample();
        ExtCproductExample.Criteria criteria = extCproductExample.createCriteria();
        criteria.andContractIdEqualTo(contractId);
        criteria.andContractProductIdEqualTo(contractProductId);
        PageInfo pageInfo = extCproductService.findAll(extCproductExample, page, size);
        model.addAttribute("page", pageInfo);
        model.addAttribute("contractId", contractId);
        model.addAttribute("contractProductId", contractProductId);
        //工厂相关
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria1 = factoryExample.createCriteria();
        criteria1.andCtypeEqualTo("附件");
        List<Factory> factories = factoryService.selectByExample(factoryExample);
        model.addAttribute("factoryList", factories);
        return "cargo/extc/extc-list";
    }

    @RequestMapping("/edit")
    public String edit(ExtCproduct extCproduct, MultipartFile productPhoto) throws IOException {
        extCproduct.setCompanyId(getLoginCompanyId());
        extCproduct.setCompanyName(getLoginCompanyName());
        if (StringUtils.isEmpty(extCproduct.getId())) {
            //保存
            if (!productPhoto.isEmpty()) {
                String photoUrl = FileUploadUtils.fileUpload(productPhoto.getBytes());
                extCproduct.setProductImage(photoUrl);
            }
            extCproductService.save(extCproduct);
        } else {
            //修改
            if (!productPhoto.isEmpty()) {
                String photoUrl1 = FileUploadUtils.fileUpload(productPhoto.getBytes());
                extCproduct.setProductImage(photoUrl1);
            }
            //System.out.println(extCproduct+"666666666666666666666666");
            extCproductService.update(extCproduct);

        }

        return "redirect:/cargo/extCproduct/list.do?contractProductId=" + extCproduct.getContractProductId() + "&contractId=" + extCproduct.getContractId();
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id, String contractId, String contractProductId, Model model) {
        //工厂相关
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria1 = factoryExample.createCriteria();
        criteria1.andCtypeEqualTo("附件");
        List<Factory> factories = factoryService.selectByExample(factoryExample);
        model.addAttribute("factoryList", factories);

        //附件相关
        ExtCproduct extCproduct = extCproductService.findById(id);
        model.addAttribute("extCproduct", extCproduct);
        return "cargo/extc/extc-update";
    }

    @RequestMapping("/delete")
    public String delete(String id, String contractId, String contractProductId, Model model) {
        extCproductService.delete(id);
        return "redirect:/cargo/extCproduct/list.do?contractProductId=" + contractProductId + "&contractId=" + contractId;

    }

}
