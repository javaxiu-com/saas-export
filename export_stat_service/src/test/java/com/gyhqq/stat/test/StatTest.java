package com.gyhqq.stat.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class StatTest {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext cs = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        cs.start();
        System.in.read();
    }
}
