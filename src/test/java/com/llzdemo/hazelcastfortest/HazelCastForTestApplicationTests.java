package com.llzdemo.hazelcastfortest;

import cn.hutool.extra.spring.SpringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@SpringBootTest
class HazelCastForTestApplicationTests {

    @Test
    void contextLoads() {
        Object preRunning = SpringUtil.getBean("preRunning");
        Class<?> runningClass = preRunning.getClass();
        Field[] fields = runningClass.getDeclaredFields();
        Method[] methods = preRunning.getClass().getMethods();
        System.out.println(methods.length+fields.length);
    }

}
