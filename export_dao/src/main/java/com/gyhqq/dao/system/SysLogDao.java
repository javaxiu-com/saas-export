package com.gyhqq.dao.system;

import com.gyhqq.domain.system.SysLog;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SysLogDao {
    //查询全部
    List<SysLog> findAll(String companyId);

    //添加
    void save(SysLog log);
}