package com.example.billing_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope
@RestController
public class ConsulConfigRestController {
    private MyConsulConfig myConsulConfig;
    public ConsulConfigRestController(MyConsulConfig myConsulConfig) {
        this.myConsulConfig = myConsulConfig;
    }

    @GetMapping("/myConfig")
    public MyConsulConfig myConfig(){
        return myConsulConfig;
    }
}
