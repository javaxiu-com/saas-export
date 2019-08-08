package com.gyhqq.web.controller.stat;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.service.stat.StatService;
import com.gyhqq.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stat")
public class StatController extends BaseController {

    @Reference
    private StatService statService;

    //传入 统计分析的数据
    @RequestMapping("/toCharts")
    public String toCharts(String chartsType) {
        return "stat/stat-" + chartsType;
    }

    //返回厂家销量的数据
    @RequestMapping("/getFactoryData")
    public @ResponseBody
    List<Map> getFactoryData() {
        List<Map> factoryData = statService.findFactoryData(getLoginCompanyId());
        return factoryData;
    }

    //返回厂家销售统计
    @RequestMapping("/getSellData")
    public @ResponseBody
    List<Map> getSellData() {
        return statService.findSellData(getLoginCompanyId());
    }

    //统计最大在线人数
    @RequestMapping("/getOnlineData")
    public @ResponseBody
    List<Map> getOnlineData() {
        return statService.findOnlineData(getLoginCompanyId());
    }

    //统计top10 的产品价格
    @RequestMapping("/getLoginTopData")
    public @ResponseBody
    List<Map> getloginTopData() {
        return statService.findloginTopData(getLoginCompanyId());
    }

    //统计top10 的登陆IP
    @RequestMapping("/getLoginIpData")
    public @ResponseBody
    List<Map> getLoginIpData() {
        return statService.findloginIpData(getLoginCompanyId());
    }

}
