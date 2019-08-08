package com.gyhqq.service.cargo;

import com.gyhqq.domain.cargo.Contract;
import com.gyhqq.domain.cargo.ContractExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ContractService {
    //根据id查询
    Contract findById(String id);

    //保存
    void save(Contract contract);

    //更新
    void update(Contract contract);

    //删除
    void delete(String id);

    //分页查询
    PageInfo findAll(ContractExample example, int page, int size);

    List<Contract> findContractByDeliveryPeriod(String time);
}
