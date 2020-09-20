package com.peng.setp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UserServicImpl implements UserService {

    public static String SERVICE_NAME = "service-demo";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String queryContent(){
        String result = restTemplate.getForObject("http://"+ SERVICE_NAME
        +"/queryUser" ,String.class);
        return result;
    }

}
