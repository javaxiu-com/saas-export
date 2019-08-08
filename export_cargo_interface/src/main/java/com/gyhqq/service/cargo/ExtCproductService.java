package com.gyhqq.service.cargo;

import com.gyhqq.domain.cargo.ExtCproduct;
import com.gyhqq.domain.cargo.ExtCproductExample;
import com.github.pagehelper.PageInfo;

public interface ExtCproductService {

    PageInfo findAll(ExtCproductExample extCproductExample, Integer page, Integer size);

    void save(ExtCproduct extCproduct);

    ExtCproduct findById(String id);

    void update(ExtCproduct extCproduct);

    void delete(String id);
}
