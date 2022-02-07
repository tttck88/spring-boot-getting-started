package com.example.springbootgettingstarted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication와 밑에 세 어노테이션은 같다.
//@SpringBootConfiguration
//@ComponentScan
//@EnableAutoConfiguration
public class SpringBootGettingStartedApplication {

    public static void main(String[] args) {
        // @EnableAutoConfiguration을 사용하지 않고 실행하고 싶은 경우는 아래와같이 하면 된다.
        // SpringApplication application = new SpringApplication(SpringBootGettingStartedApplication.class);
        // 웹서버로 실행하는것이 아니기 때문에
        // application.setWebApplicationType(WebApplicationType.NONE);
        // application.run(args);

        SpringApplication.run(SpringBootGettingStartedApplication.class, args);
    }

}
