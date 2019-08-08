package com.gyhqq.dao.cargo;

import com.gyhqq.domain.cargo.ContractProduct;
import com.gyhqq.domain.cargo.ContractProductExample;
import com.gyhqq.vo.ContractProductVo;

import java.util.List;

public interface ContractProductDao {

    //删除
    int deleteByPrimaryKey(String id);

    //保存
    int insertSelective(ContractProduct record);

    //条件查询
    List<ContractProduct> selectByExample(ContractProductExample example);

    //id查询
    ContractProduct selectByPrimaryKey(String id);

    //更新
    int updateByPrimaryKeySelective(ContractProduct record);

    List<ContractProductVo> findByDate(String inputDate);
}