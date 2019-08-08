package com.gyhqq.service.cargo.impl;

import com.gyhqq.dao.cargo.ExportDao;
import com.gyhqq.dao.cargo.InvoiceDao;
import com.gyhqq.dao.cargo.PackingListDao;
import com.gyhqq.domain.cargo.Export;
import com.gyhqq.domain.cargo.PackingList;
import com.gyhqq.service.cargo.InvoiceService;

import com.gyhqq.domain.cargo.Invoice;
import com.gyhqq.domain.cargo.InvoiceExample;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private PackingListDao packingListDao;
    @Autowired
    private ExportDao exportDao;

    public Invoice findById(String id) {
        return invoiceDao.selectByPrimaryKey(id);
    }

    public void save(Invoice invoice) {
        invoice.setState(0);
        invoiceDao.insertSelective(invoice);

        PackingList packingList = packingListDao.selectByPrimaryKey(invoice.getInvoiceId());
        String exportIds = packingList.getExportIds();
        String[] ids = exportIds.split(",");
        for (String id : ids) {
            Export export = new Export();
            export.setId(id);
            export.setState(6);
            exportDao.updateByPrimaryKeySelective(export);
        }
    }

    public void update(Invoice invoice) {
        invoiceDao.updateByPrimaryKeySelective(invoice);
    }

    public void delete(String invoiceId) {
        invoiceDao.deleteByPrimaryKey(invoiceId);
    }

    public PageInfo findAll(InvoiceExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Invoice> list = invoiceDao.selectByExample(example);
        return new PageInfo(list);
    }

    public List<Invoice> findByState(InvoiceExample example) {
        return invoiceDao.selectByExample(example);
    }
}
