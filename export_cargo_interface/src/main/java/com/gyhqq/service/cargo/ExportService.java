package com.gyhqq.service.cargo;

import com.gyhqq.domain.cargo.Export;
import com.gyhqq.domain.cargo.ExportExample;
import com.gyhqq.vo.ExportResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExportService {
    //根据id查询
    Export findById(String id);

    //保存
    void save(Export export);

    //更新
    void update(Export export);

    //删除
    void delete(String id);

    //分页查询
    PageInfo findAll(ExportExample example, int page, int size);

    void updateResult(ExportResult exportResult);

    List<Export> findStateExport(ExportExample exportExample);
}
