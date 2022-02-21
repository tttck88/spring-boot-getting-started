package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Value("${tttck.name}")
    private String name;

    @Value("${tttck.age}")
    private String age;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("name");
    }
}
