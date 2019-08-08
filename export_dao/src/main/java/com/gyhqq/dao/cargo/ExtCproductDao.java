package com.gyhqq.dao.cargo;

import com.gyhqq.domain.cargo.ExtCproduct;
import com.gyhqq.domain.cargo.ExtCproductExample;

import java.util.List;

public interface ExtCproductDao {

    //删除
    void deleteByPrimaryKey(String id);

    //保存
    void insertSelective(ExtCproduct record);

    //条件查询
    List<ExtCproduct> selectByExample(ExtCproductExample example);

    //id查询
    ExtCproduct selectByPrimaryKey(String id);

    //更新
    void updateByPrimaryKeySelective(ExtCproduct record);

}