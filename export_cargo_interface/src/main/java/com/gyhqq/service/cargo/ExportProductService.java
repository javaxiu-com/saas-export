package com.gyhqq.service.cargo;

import com.gyhqq.domain.cargo.ExportProduct;
import com.gyhqq.domain.cargo.ExportProductExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExportProductService {
    //根据id查询
    ExportProduct findById(String id);

    List<ExportProduct> findAll(ExportProductExample example);
}
