package com.gyhqq.service.stat.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gyhqq.dao.stat.StatDao;
import com.gyhqq.service.stat.StatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService {
    @Autowired
    private StatDao statDao;

    @Override
    public List<Map> findFactoryData(String loginCompanyId) {
        return statDao.findFactoryData(loginCompanyId);
    }

    @Override
    public List<Map> findSellData(String loginCompanyId) {
        return statDao.findSellData(loginCompanyId);
    }

    @Override
    public List<Map> findOnlineData(String loginCompanyId) {
        return statDao.findOnlineData(loginCompanyId);
    }

    @Override
    public List<Map> findloginTopData(String loginCompanyId) {
        List<Map> maps = statDao.findloginTopData(loginCompanyId);
        return maps;
    }

    @Override
    public List<Map> findloginIpData(String loginCompanyId) {
        return statDao.findloginIpData(loginCompanyId);
    }

}
