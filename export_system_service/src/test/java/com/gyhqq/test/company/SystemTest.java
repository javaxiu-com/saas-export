package com.gyhqq.test.company;

import com.gyhqq.domain.system.User;
import com.gyhqq.service.company.CompanyService;
import com.gyhqq.service.system.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class SystemTest {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Test
    public void test1() {
       /* List<Company> list = companyService.findAll(1,3);
        for (Company company : list) {
            System.out.println(company);
        }*/
    }

    @Test
    public void test2() {
        List<User> list = userService.findAllNotMime("1", "002108e2-9a10-4510-9683-8d8fd1d374ef");
        for (User user : list) {
            System.out.println(user);
        }

    }
}
