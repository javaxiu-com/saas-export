package com.gyhqq.test.message;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class MessageTest {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext cs = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        cs.start();
        System.in.read();
    }
}
