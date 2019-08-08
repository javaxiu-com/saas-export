package com.gyhqq.test.system;

import com.gyhqq.dao.system.UserDao;
import com.gyhqq.domain.system.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserTest {
    @Test
    public void test1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
        UserDao userDao = ac.getBean(UserDao.class);
        List<User> list = userDao.findAllNotMime("1", "002108e2-9a10-4510-9683-8d8fd1d374ef");
        for (User user : list) {
            System.out.println(user);
        }

    }
}
