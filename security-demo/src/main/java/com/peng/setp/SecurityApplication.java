package com.peng.setp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableEurekaClient
/*
* 对外暴露接口
* */
@EnableResourceServer
public class SecurityApplication {
    public static void main(String[] args){
        SpringApplication.run(SecurityApplication.class,args);
    }
}
