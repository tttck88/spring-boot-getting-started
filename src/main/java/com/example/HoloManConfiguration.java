package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HolomanProperties.class)
public class HoloManConfiguration {

    @Bean
    @ConditionalOnMissingBean // 이 타입의 빈이 없을 경우에만 등록
    public Holoman holoman(HolomanProperties holomanProperties) {
        Holoman holoman = new Holoman();
        holoman.setHowLong(holomanProperties.getHowLong());
        holoman.setName(holomanProperties.getName());
        return holoman;
    }
}
