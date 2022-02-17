package com.example;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerTest implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // VM options : -Dfoo
        System.out.println("foo: " + args.containsOption("foo"));
        // Program arguments : --bar
        System.out.println("bar: " + args.containsOption("bar"));
    }
}
