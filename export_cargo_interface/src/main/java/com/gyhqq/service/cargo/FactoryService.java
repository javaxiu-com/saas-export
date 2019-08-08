package com.gyhqq.service.cargo;

import com.gyhqq.domain.cargo.Factory;
import com.gyhqq.domain.cargo.FactoryExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FactoryService {

    //删除
    void deleteByPrimaryKey(String id);

    //保存
    void insertSelective(Factory record);

    //条件查询
    List<Factory> selectByExample(FactoryExample example);

    //id查询
    Factory selectByPrimaryKey(String id);

    //更新
    void updateByPrimaryKeySelective(Factory record);

    //分页查询的条件查询
    PageInfo findAll(FactoryExample example, Integer page, Integer size);

    //保存新增厂家
    void save(Factory factory);

    //更新厂家信息
    void update(Factory factory);
}
