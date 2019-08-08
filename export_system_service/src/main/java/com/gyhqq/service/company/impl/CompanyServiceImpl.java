package com.gyhqq.service.company.impl;

import com.gyhqq.dao.company.CompanyDao;
import com.gyhqq.domain.company.Company;

import com.gyhqq.service.company.CompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

   /* @Override
    public List<Company> findAll(PageBean<Company> pageBean) {
        List<Company> list = companyDao.findAll(pageBean.getIndexPage(),pageBean.getPageSize());
        return list;
    }*/

    /*@Override
    public Integer findTotalRecord() {
        return companyDao.findTotalRecord();
    }*/

    @Override
    public void save(Company company) {
        company.setId(UUID.randomUUID().toString());
        //int i=1/0;
        companyDao.save(company);
    }

    //根据id查company对象
    public Company findById(String id) {
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

    public PageInfo findPageByHelp(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Company> list = companyDao.findAll();
        return new PageInfo(list);

    }














   /* //自定义分页
    public PageResult findAll(Integer pageNum, Integer pageSize) {
        List<Company> list = companyDao.findAll((pageNum - 1) * pageSize, pageSize);
        Integer total = companyDao.findTotalRecord();
        return new PageResult(total,list,pageNum,pageSize);

    }*/
}
