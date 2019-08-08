package com.gyhqq.service.stat;

import java.util.List;
import java.util.Map;

public interface StatService {
    List<Map> findFactoryData(String loginCompanyId);

    List<Map> findSellData(String loginCompanyId);

    List<Map> findOnlineData(String loginCompanyId);

    List<Map> findloginTopData(String loginCompanyId);

    List<Map> findloginIpData(String loginCompanyId);

    /*List<Map> findloginTopData(String loginCompanyId);*/
}
