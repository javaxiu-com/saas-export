package com.gyhqq.dao.stat;

import java.util.List;
import java.util.Map;

public interface StatDao {
    List<Map> findFactoryData(String loginCompanyId);

    List<Map> findSellData(String loginCompanyId);

    List<Map> findOnlineData(String loginCompanyId);

    List<Map> findloginTopData(String loginCompanyId);

    List<Map> findloginIpData(String loginCompanyId);
}
