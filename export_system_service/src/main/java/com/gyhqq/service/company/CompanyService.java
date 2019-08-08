package com.gyhqq.service.company;

import com.gyhqq.domain.company.Company;
import com.github.pagehelper.PageInfo;

public interface CompanyService {

    void save(Company company);

    Company findById(String id);

    void updateCompany(Company company);

    void delete(String id);

    PageInfo findPageByHelp(Integer pageNum, Integer pageSize);
}
