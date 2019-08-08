package com.gyhqq.service.cargo;

import com.gyhqq.domain.cargo.ContractProduct;
import com.gyhqq.domain.cargo.ContractProductExample;
import com.gyhqq.vo.ContractProductVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ContractProductService {

    PageInfo findAll(ContractProductExample cpExample, Integer page, Integer size);

    void save(ContractProduct contractProduct);

    ContractProduct findById(String id);

    void update(ContractProduct contractProduct);

    void delete(String id, String contractId);

    void toImport(List<ContractProduct> list);

    List<ContractProductVo> findByDate(String inputDate);
}
