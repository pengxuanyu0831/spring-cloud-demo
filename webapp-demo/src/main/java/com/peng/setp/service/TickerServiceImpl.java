package com.peng.setp.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public class TickerServiceImpl implements TickerService {



    @Override
    @HystrixCommand
    public String TickerService() {
        return "TickerService";
    }

    @Override
    @HystrixCommand
    public String SaveService() {
        return "SaveService";
    }
}
