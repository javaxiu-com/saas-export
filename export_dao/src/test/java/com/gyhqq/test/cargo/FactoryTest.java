package com.gyhqq.test.cargo;

import com.gyhqq.dao.cargo.FactoryDao;
import com.gyhqq.domain.cargo.Factory;
import com.gyhqq.domain.cargo.FactoryExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")
public class FactoryTest {

    @Autowired
    private FactoryDao factoryDao;

    @Test
    public void testSelectByExample() {
        FactoryExample example = new FactoryExample();
        FactoryExample.Criteria criteria = example.createCriteria();
        criteria.andCtypeEqualTo("货物");
        criteria.andAddressEqualTo("长沙");
        List<Factory> factories = factoryDao.selectByExample(example);
        for (Factory factory : factories) {
            System.out.println(factory);
        }
    }

}
