package com.gyhqq.service.company;

import com.gyhqq.domain.company.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    void save(Company company);

    Company toUpdate(String id);

    void updateCompany(Company company);

    void delete(String id);

}
