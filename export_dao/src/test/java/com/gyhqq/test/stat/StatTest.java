package com.gyhqq.test.stat;

import com.gyhqq.dao.stat.StatDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class StatTest {

    @Test
    public void test3() throws JsonProcessingException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
        StatDao statDao = ac.getBean(StatDao.class);
        List<Map> factoryData = statDao.findFactoryData("1");
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(factoryData);
        System.out.println(string);

    }
}
