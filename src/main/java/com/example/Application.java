package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Application.class);
        // 아래 이벤트는 애플리케이션 컨텍스트가 실행되기 이전에 발생하기 떄문에 bean으로 등록하는 것이 아닌, 작접 등록해야함
//        app.addListeners(new SampleListener());
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}
