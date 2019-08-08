package com.gyhqq.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class JmsTest {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mq-consumer.xml");
        ac.start();
        System.in.read();

    }
}
