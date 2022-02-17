package com.example;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

// 애플리케이션 컨텍스트가 실행되기 이전에 생성되기때문에 직접등록하지 않고 bean으로 등록하면 실행히 안된다.
//@Component
public class SampleListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        System.out.println("Application is starting");
    }
}
