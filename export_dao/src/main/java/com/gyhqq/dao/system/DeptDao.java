package com.gyhqq.dao.system;

import com.gyhqq.domain.system.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DeptDao {

    List<Dept> findAll(String companyId);

    Dept findById(String id);

    void save(Dept dept);

    void update(Dept dept);

    void delete(String id);
}
