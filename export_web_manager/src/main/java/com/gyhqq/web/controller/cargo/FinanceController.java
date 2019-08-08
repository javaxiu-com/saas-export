package com.gyhqq.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.domain.cargo.Finance;
import com.gyhqq.domain.cargo.FinanceExample;
import com.gyhqq.domain.cargo.Invoice;
import com.gyhqq.domain.cargo.InvoiceExample;
import com.gyhqq.service.cargo.FinanceService;
import com.gyhqq.service.cargo.InvoiceService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cargo/finance")
public class FinanceController extends BaseController {

    @Reference
    private FinanceService financeService;

    @Reference
    private InvoiceService invoiceService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "5") int size) {
        //查询所有状态为已上报(state=6)的所有财务报运单
        FinanceExample example = new FinanceExample();

        PageInfo info = financeService.findAll(example, page, size);
        request.setAttribute("page", info);
        return "cargo/finance/finance-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        //查询所有状态为已上报(state=1)的所有发票单
        InvoiceExample example = new InvoiceExample();
        InvoiceExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(1);
        List<Invoice> info = invoiceService.findByState(example);
        request.setAttribute("invoice", info);
        return "cargo/finance/finance-add";
    }

    /**
     * 新增或者修改报运单
     */
    @RequestMapping("/edit")
    public String edit(Finance finance, String invoiceId) {
        if (StringUtils.isEmpty(finance.getFinanceId())) {
            //保存
            finance.setFinanceId(invoiceId);
            financeService.save(finance);
            Invoice invoice = new Invoice();
            invoice.setInvoiceId(invoiceId);
            invoice.setState(2);
            invoiceService.update(invoice);
        } else {
            financeService.update(finance);
        }
        return "redirect:/cargo/finance/list.do";
    }

    /**
     * 进入到修改页面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(String financeId) {
        Finance export = financeService.findById(financeId);
        request.setAttribute("finance", export);
        return "cargo/finance/finance-update";
    }

    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public String delete(String financeId) {
        financeService.delete(financeId);
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(financeId);
        invoice.setState(1);
        invoiceService.update(invoice);
        return "redirect:/cargo/finance/list.do";
    }

    /**
     * 提交
     * 参数：报运单id
     * 将状态由0改为1
     */
    @RequestMapping("/submit")
    public String submit(String financeId) {
        Finance finance = new Finance();
        finance.setFinanceId(financeId);
        finance.setState(1);
        financeService.update(finance);
        return "redirect:/cargo/finance/list.do";
    }

    /**
     * 取消
     * 参数：报运单id
     * 将状态由1改为0
     */
    @RequestMapping("/cancel")
    public String cancel(String financeId) {
        Finance finance = new Finance();
        finance.setFinanceId(financeId);
        finance.setState(0);
        financeService.update(finance);
        return "redirect:/cargo/finance/list.do";
    }
}
