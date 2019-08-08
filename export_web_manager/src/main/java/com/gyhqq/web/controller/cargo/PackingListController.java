package com.gyhqq.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.domain.cargo.Export;
import com.gyhqq.domain.cargo.ExportExample;
import com.gyhqq.domain.cargo.PackingList;
import com.gyhqq.domain.cargo.PackingListExample;
import com.gyhqq.service.cargo.ExportService;
import com.gyhqq.service.cargo.PackingListService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cargo/packing")
public class PackingListController extends BaseController {

    @Reference
    private PackingListService packingListService;

    @Reference
    private ExportService exportService;

    @RequestMapping("/list")
    public String list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        PackingListExample example = new PackingListExample();
        PackingListExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(getLoginCompanyId());

        PageInfo info = packingListService.findAll(page, size, example);
        request.setAttribute("page", info);

        return "cargo/packingList/packingList-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "5") int size, Model model) {
        //去数据库查询所有已报运的报运单(state为2),
        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andCompanyIdEqualTo(getLoginCompanyId());
        criteria.andStateEqualTo(2);
        PageInfo pageInfo = exportService.findAll(exportExample, page, size);
        //List<Export> exports = exportService.findStateExport(exportExample);
        model.addAttribute("page", pageInfo);
        return "cargo/packingList/packingList-add";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        PackingList packingList = packingListService.findById(id);
        model.addAttribute("packing", packingList);

        String[] exportIds = packingList.getExportIds().split(",");
        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andIdIn(Arrays.asList(exportIds));
        List<Export> eps = exportService.findStateExport(exportExample);
        model.addAttribute("eps", eps);
        return "cargo/packingList/packingList-update";
    }

    @RequestMapping("/edit")
    public String edit(PackingList packingList) {
        packingList.setCompanyId(getLoginCompanyId());
        packingList.setCompanyName(getLoginCompanyName());
        if (StringUtils.isEmpty(packingList.getPackingListId())) {
            //保存,保存后将报运单的状态改为4已装箱
            packingListService.save(packingList);

        } else {
            //修改
            packingListService.update(packingList);
        }
        return "redirect:/cargo/packing/list.do";
    }

    @RequestMapping("/delete")
    public String delete(String id) {

        PackingList packingList = packingListService.findById(id);
        String[] exportIds = packingList.getExportIds().split(",");
        for (String exportId : exportIds) {
            Export export = new Export();
            export.setId(exportId);
            export.setState(2);
            exportService.update(export);
        }
        packingListService.delete(id);
        return "redirect:/cargo/packing/list.do";
    }

    /**
     * 提交方法:
     * 参数:报运单的id
     * 将报运单状态由0改成1
     */
    @RequestMapping("/submit")
    public String submit(String packingListId) {
        PackingList packingList = new PackingList();
        packingList.setPackingListId(packingListId);
        packingList.setState(1);
        packingListService.update(packingList);
        return "redirect:/cargo/packing/list.do";
    }

    /**
     * 取消方法:
     * 参数:报运单的id
     * 将报运单状态由1改成0
     */
    @RequestMapping("/cancel")
    public String cancel(String packingListId) {
        PackingList packingList = new PackingList();
        packingList.setPackingListId(packingListId);
        packingList.setState(0);
        packingListService.update(packingList);
        return "redirect:/cargo/packing/list.do";
    }

}
