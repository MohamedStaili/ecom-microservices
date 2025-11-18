package com.example.billing_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//@RefreshScope pas besoin lorsq'on utilise ConfigrationProperties
@RestController
public class ConsulConfigRestController {
    private MyConsulConfig myConsulConfig;
    private MyVaultConfig myVaultConfig;
    public ConsulConfigRestController(MyConsulConfig myConsulConfig, MyVaultConfig myVaultConfig) {
        this.myConsulConfig = myConsulConfig;
        this.myVaultConfig = myVaultConfig;
    }
    @GetMapping("/myConfig")
    public Map<String,Object> myConfig(){
        return Map.of("config", myConsulConfig, "vault", myVaultConfig);
    }
}
