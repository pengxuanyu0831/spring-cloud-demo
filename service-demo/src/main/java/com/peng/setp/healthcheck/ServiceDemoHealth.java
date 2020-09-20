package com.peng.setp.healthcheck;

import com.peng.setp.controller.ServiceDemoController;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceDemoHealth implements HealthIndicator {
    @Override
    public Health health() {
        //这个状态就是数据库是否连接OK
        if(ServiceDemoController.canConnectDb) {
            return new Health.Builder(Status.UP).build();
        } else {
            return new Health.Builder(Status.DOWN).build();
        }
    }
}
