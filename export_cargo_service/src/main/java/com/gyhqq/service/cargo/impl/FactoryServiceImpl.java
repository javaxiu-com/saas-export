package com.gyhqq.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gyhqq.dao.cargo.FactoryDao;
import com.gyhqq.domain.cargo.Factory;
import com.gyhqq.domain.cargo.FactoryExample;
import com.gyhqq.service.cargo.FactoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryDao factoryDao;

    @Override
    public void deleteByPrimaryKey(String id) {
        factoryDao.deleteByPrimaryKey(id);

    }

    @Override
    public void insertSelective(Factory record) {
        record.setId(UUID.randomUUID().toString());
        factoryDao.insertSelective(record);
    }

    @Override
    public List<Factory> selectByExample(FactoryExample example) {
        return factoryDao.selectByExample(example);
    }

    @Override
    public Factory selectByPrimaryKey(String id) {
        return factoryDao.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(Factory record) {
        factoryDao.updateByPrimaryKeySelective(record);
    }

    //分页查询的条件查询
    @Override
    public PageInfo findAll(FactoryExample example, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Factory> list = factoryDao.selectByExample(example);
        return new PageInfo(list);
    }

    //保存新增厂家
    @Override
    public void save(Factory factory) {
        factory.setId(UUID.randomUUID().toString());
        factory.setCreateTime(new Date());
        factoryDao.insertSelective(factory);
    }

    //更新厂家信息
    @Override
    public void update(Factory factory) {
        factory.setUpdateTime(new Date());
        factoryDao.updateByPrimaryKeySelective(factory);
    }
}
