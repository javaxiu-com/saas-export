package com.gyhqq.dao.company;

import com.gyhqq.domain.company.Company;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CompanyDao {

    List<Company> findAll();

    Integer findTotalRecord();

    void save(Company company);

    Company findById(String id);

    void updateCompany(Company company);

    void delete(String id);
}
