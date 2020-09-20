package com.peng.setp.controller;


import com.peng.setp.service.UserServicImpl;
import com.peng.setp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ServiceDemoController {
    @Autowired
    private UserService userService;

    @RequestMapping("/queryUser")
    public String queryUser(){
        return userService.queryContent();
    }

    public static boolean canConnectDb =true;

    @RequestMapping("/setDb")
    public void setDb(@PathVariable boolean can){
       canConnectDb = can;
    }





}
