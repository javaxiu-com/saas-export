package com.gyhqq.service.system.impl;

import com.gyhqq.dao.system.SysLogDao;
import com.gyhqq.domain.system.SysLog;
import com.gyhqq.service.system.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public PageInfo findAll(Integer page, Integer size, String companyId) {
        PageHelper.startPage(page, size);
        List<SysLog> list = sysLogDao.findAll(companyId);
        return new PageInfo(list);
    }

    @Override
    public void save(SysLog log) {
        log.setId(UUID.randomUUID().toString());
        sysLogDao.save(log);
    }
}
