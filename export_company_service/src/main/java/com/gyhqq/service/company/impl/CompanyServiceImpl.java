package com.gyhqq.service.company.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gyhqq.dao.company.CompanyDao;
import com.gyhqq.domain.company.Company;

import com.gyhqq.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> findAll() {

        return companyDao.findAll();
    }

    @Override
    public void save(Company company) {
        company.setId(UUID.randomUUID().toString());
        //int i=1/0;
        companyDao.save(company);
    }

    //根据id查company对象
    @Override
    public Company toUpdate(String id) {
        /**
         * 未知找不到
         */
        return companyDao.findById(id);

    }

    //更新
    public void updateCompany(Company company) {
        companyDao.updateCompany(company);
    }

    //删除
    public void delete(String id) {
        companyDao.delete(id);
    }

}
