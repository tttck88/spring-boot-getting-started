package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Autowired
    private String hello;

    @Autowired
    TttckProperties properties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(properties.getName());
        System.out.println(properties.getFullName());
        System.out.println(hello);
    }
}
