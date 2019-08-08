package com.gyhqq.service.system;

import com.gyhqq.domain.system.SysLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysLogService {
    PageInfo findAll(Integer page, Integer size, String companyId);

    //添加
    void save(SysLog log);

}
