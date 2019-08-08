package com.gyhqq.service.cargo;

import com.gyhqq.domain.cargo.Invoice;
import com.gyhqq.domain.cargo.InvoiceExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface InvoiceService {

    //根据id查询
    Invoice findById(String id);

    //保存
    void save(Invoice invoice);

    //更新
    void update(Invoice invoice);

    //删除
    void delete(String invoiceId);

    //查询列表分页
    PageInfo findAll(InvoiceExample example, int page, int size);

    List<Invoice> findByState(InvoiceExample example);

}
