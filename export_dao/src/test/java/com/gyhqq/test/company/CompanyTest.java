package com.gyhqq.test.company;

import com.gyhqq.dao.company.CompanyDao;
import com.gyhqq.dao.system.DeptDao;
import com.gyhqq.dao.system.UserDao;
import com.gyhqq.domain.company.Company;
import com.gyhqq.domain.system.Dept;
import com.gyhqq.domain.system.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyTest {
    @Test
    public void test1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
        CompanyDao bean = ac.getBean(CompanyDao.class);
        List<Company> list = bean.findAll();
        for (Company company : list) {
            System.out.println(company);
        }
    }

    @Test
    public void test2() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
        DeptDao deptDao = ac.getBean(DeptDao.class);
        List<Dept> list = deptDao.findAll("1");
        System.out.println(list);
    }

    @Test
    public void test3() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
        UserDao userDao = ac.getBean(UserDao.class);
        List<User> list = userDao.findAll("1");
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test4() throws JsonProcessingException {
        //map是一个双列集合,
        //map是一个集合
        //map是一个集合
        Map map = new HashMap();
        map.put("name", "12");
        map.put("value", "123");
        Map map1 = new HashMap();
        map1.put("name", "34");
        map1.put("value", "345");
        List list = new ArrayList();
        list.add(map);
        list.add(map1);
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(list);
        System.out.println(string);

    }
}
