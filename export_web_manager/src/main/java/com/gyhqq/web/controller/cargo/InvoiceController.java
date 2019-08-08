package com.gyhqq.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.domain.cargo.Invoice;
import com.gyhqq.domain.cargo.InvoiceExample;
import com.gyhqq.domain.cargo.ShippingOrderExample;
import com.gyhqq.service.cargo.InvoiceService;
import com.gyhqq.service.cargo.ShippingOrderService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cargo/invoice")
public class InvoiceController extends BaseController {

    @Reference
    private InvoiceService invoiceService;
    @Reference
    private ShippingOrderService shippingOrderService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "5") int size) {
        InvoiceExample example = new InvoiceExample();
        PageInfo info = invoiceService.findAll(example, page, size);
        request.setAttribute("page", info);
        return "cargo/invoice/invoice-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "5") int size) {

//        //查询所有状态为已上报(state=1)的所有委托单
//        InvoiceExample.Criteria criteria = example.createCriteria();
//        criteria.andStateEqualTo(1);
//        List<Invoice> info = invoiceService.findByState(example);
//        request.setAttribute("invoice",info);
        ShippingOrderExample example = new ShippingOrderExample();
        ShippingOrderExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(1);
        PageInfo pageInfo = shippingOrderService.findAll(example, page, size);
        request.setAttribute("page", pageInfo);
        return "cargo/invoice/invoice-add";
    }

    /**
     * 新增或者修改报运单
     */
    @RequestMapping("/edit")
    public String edit(Invoice invoice, String id) {
        if (StringUtils.isEmpty(invoice.getInvoiceId())) {
            //保存
            invoice.setInvoiceId(id);
            System.out.println("6666666666666666" + invoice);
            invoiceService.save(invoice);
        } else {
            invoiceService.update(invoice);
        }
        return "redirect:/cargo/invoice/list.do";
    }

    /**
     * 进入到修改页面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(String invoiceId) {
        Invoice export = invoiceService.findById(invoiceId);
        request.setAttribute("invoice", export);
        return "cargo/invoice/invoice-update";
    }

    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public String delete(String invoiceId) {
        invoiceService.delete(invoiceId);
        return "redirect:/cargo/finance/list.do";
    }

    /**
     * 提交
     * 参数：报运单id
     * 将状态由0改为1
     */
    @RequestMapping("/submit")
    public String submit(String invoiceId) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceId);
        invoice.setState(1);
        invoiceService.update(invoice);
        return "redirect:/cargo/invoice/list.do";
    }

    /**
     * 取消
     * 参数：报运单id
     * 将状态由1改为0
     */
    @RequestMapping("/cancel")
    public String cancel(String invoiceId) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceId);
        invoice.setState(0);
        invoiceService.update(invoice);
        return "redirect:/cargo/invoice/list.do";
    }
}
