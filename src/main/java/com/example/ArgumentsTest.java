package com.example;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class ArgumentsTest {

    public ArgumentsTest(ApplicationArguments arguments) {
        // VM options : -Dfoo
        System.out.println("foo: " + arguments.containsOption("foo"));
        // Program arguments : --bar
        System.out.println("bar: " + arguments.containsOption("bar"));
    }
}
