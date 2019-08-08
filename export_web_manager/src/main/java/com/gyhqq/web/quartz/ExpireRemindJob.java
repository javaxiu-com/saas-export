package com.gyhqq.web.quartz;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.domain.cargo.Contract;
import com.gyhqq.domain.system.User;
import com.gyhqq.service.cargo.ContractService;
import com.gyhqq.service.system.UserService;
import com.gyhqq.web.activemq.EmailProducer;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpireRemindJob {
    @Reference
    private ContractService contractService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailProducer emailProducer;

    public void remind() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(new Date());
        List<Contract> contracts = contractService.findContractByDeliveryPeriod(time);
        for (Contract contract : contracts) {
            String contractNO = contract.getContractNo();
            List<User> users = userService.findByCondition(contract);
            for (User user : users) {
                String email = user.getEmail();
                String subject = "您好,贵公司合同上的货物将在今天交货";
                String deliveryPeriod = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(contract.getDeliveryPeriod());
                String content = "您好,贵公司合同号为:" + contractNO + "的合同,其交货日期为" + deliveryPeriod + ".请您及时做出处理!";
                Map<String, String> map = new HashMap<>();
                map.put("receive", email);
                map.put("subject", subject);
                map.put("content", content);
                emailProducer.sendMessage(map);
            }

        }

    }
}
