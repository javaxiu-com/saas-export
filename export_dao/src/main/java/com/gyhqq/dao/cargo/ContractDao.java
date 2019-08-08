package com.gyhqq.dao.cargo;

import com.gyhqq.domain.cargo.Contract;
import com.gyhqq.domain.cargo.ContractExample;

import java.util.List;

public interface ContractDao {

    //删除
    void deleteByPrimaryKey(String id);

    //保存
    void insertSelective(Contract record);

    //条件查询
    List<Contract> selectByExample(ContractExample example);

    //id查询
    Contract selectByPrimaryKey(String id);

    //更新
    void updateByPrimaryKeySelective(Contract record);

    List<Contract> findContractByDeliveryPeriod(String time);
}