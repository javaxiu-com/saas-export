package com.gyhqq.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.domain.cargo.*;
import com.gyhqq.service.cargo.ContractService;
import com.gyhqq.service.cargo.ExportProductService;
import com.gyhqq.service.cargo.ExportService;
import com.gyhqq.vo.ExportProductVo;
import com.gyhqq.vo.ExportVo;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cargo/export")
public class ExportController extends BaseController {

    @Reference
    private ContractService contractService;
    @Reference
    private ExportService exportService;
    @Reference
    private ExportProductService exportProductService;

    @RequestMapping("/contractList")
    public String contractList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, Model model) {
        ContractExample example = new ContractExample();
        ContractExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(getLoginCompanyId());
        criteria.andStateEqualTo(1);

        example.setOrderByClause("create_time desc");
        PageInfo info = contractService.findAll(example, page, size);
        model.addAttribute("page", info);
        return "/cargo/export/export-contractList";
    }

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, Model model) {
        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andCompanyIdEqualTo(getLoginCompanyId());
        PageInfo pageinfo = exportService.findAll(exportExample, page, size);
        model.addAttribute("page", pageinfo);
        return "/cargo/export/export-list";
    }

    @RequestMapping("/toExport")
    public String toExport(String id, Model model) {
        model.addAttribute("id", id);
        return "/cargo/export/export-toExport";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        Export export = exportService.findById(id);
        model.addAttribute("export", export);
        ExportProductExample exportProductExample = new ExportProductExample();
        ExportProductExample.Criteria criteria = exportProductExample.createCriteria();
        criteria.andExportIdEqualTo(id);
        List<ExportProduct> list = exportProductService.findAll(exportProductExample);
        model.addAttribute("eps", list);
        return "/cargo/export/export-update";
    }

    @RequestMapping("/edit")
    public String edit(Export export) {
        export.setCompanyId(getLoginCompanyId());
        export.setCompanyName(getLoginCompanyName());
        if (StringUtils.isEmpty(export.getId())) {
            exportService.save(export);
        } else {
            exportService.update(export);
        }
        return "redirect:/cargo/export/list.do";
    }

    //提交
    @RequestMapping("/submit")
    public String submit(String id) {
        Export export = exportService.findById(id);
        export.setState(1);
        exportService.update(export);
        return "redirect:/cargo/export/list.do";
    }

    //取消
    @RequestMapping("cancel")
    public String cancel(String id) {
        Export export = exportService.findById(id);
        export.setState(0);
        exportService.update(export);
        return "redirect:/cargo/export/list.do";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(String id) {
        exportService.delete(id);
        return "redirect:/cargo/export/list.do";
    }

    @RequestMapping("/exportE")
    public String exportE(String id) {
        //实现的功能,用rs规范使用海关接口,提交报运单信息,再查询海关报运是否成功.更改报运单状态
        //给报运货物添加税收信息,
        //1 根据id找到报运单
        Export export = exportService.findById(id);
        //2 填充exportvo,
        ExportVo exportVo = new ExportVo();
        BeanUtils.copyProperties(export, exportVo);
        exportVo.setExportId(id);
        //3 填充exportProductvo
        ExportProductExample exportProductExample = new ExportProductExample();
        ExportProductExample.Criteria criteria = exportProductExample.createCriteria();
        criteria.andExportIdEqualTo(id);
        List<ExportProduct> exportProducts = exportProductService.findAll(exportProductExample);
        List<ExportProductVo> exportProductVos = new ArrayList<>();
        for (ExportProduct exportProduct : exportProducts) {
            ExportProductVo exportProductVo = new ExportProductVo();
            BeanUtils.copyProperties(exportProduct, exportProductVo);
            exportProductVo.setExportId(id);
            exportProductVo.setExportProductId(exportProduct.getId());
            exportProductVos.add(exportProductVo);
        }
        exportVo.setProducts(exportProductVos);
        //4 使用webclient提交exportvo给海关
        WebClient webClient = WebClient.create("http://localhost:8081/ws/export/user");
        webClient.post(exportVo);
        //5 使用webClient查询报运信息
        /* webClient = webClient.create("http://localhost:8081/ws/export/user/" + id);
        ExportResult exportResult = webClient.get(ExportResult.class);*/
        //6 更改报运单状态为已发送
        export.setState(3);
        exportService.update(export);
        //7 给报运货物添加税收
        /*exportService.updateResult(exportResult);*/
        return "redirect:/cargo/export/list.do";
    }

}
