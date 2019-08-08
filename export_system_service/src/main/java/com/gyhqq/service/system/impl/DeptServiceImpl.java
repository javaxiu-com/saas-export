package com.gyhqq.service.system.impl;

import com.gyhqq.dao.system.DeptDao;
import com.gyhqq.domain.system.Dept;
import com.gyhqq.service.system.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    //分页查询
    public PageInfo findPageByHelper(Integer page, Integer size, String companyId) {
        PageHelper.startPage(page, size);
        List<Dept> list = deptDao.findAll(companyId);
        return new PageInfo(list);
    }

    @Override
    public List<Dept> findAll(String companyId) {
        List<Dept> list = deptDao.findAll(companyId);
        return list;
    }

    @Override
    public void save(Dept dept) {
        dept.setId(UUID.randomUUID().toString());
        deptDao.save(dept);
    }

    @Override
    public Dept findById(String id) {
        return deptDao.findById(id);
    }

    @Override
    public void update(Dept dept) {
        deptDao.update(dept);
    }

    @Override
    public void delete(String id) {
        deptDao.delete(id);
    }
}
