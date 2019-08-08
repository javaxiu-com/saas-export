package com.gyhqq.service.system;

import com.gyhqq.domain.system.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DeptService {

    PageInfo findPageByHelper(Integer page, Integer size, String companyId);

    List<Dept> findAll(String companyId);

    void save(Dept dept);

    Dept findById(String id);

    void update(Dept dept);

    void delete(String id);
}
