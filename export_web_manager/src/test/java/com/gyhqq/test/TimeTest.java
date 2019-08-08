package com.gyhqq.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {
    @Test
    public void timeTest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(new Date());
        System.out.println(time);
    }

    @Test
    public void timeTest2() {
        String deliveryPeriod = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(deliveryPeriod);

    }
}
