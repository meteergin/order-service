/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meteergin.orderservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author mergin
 */
@Configuration
public class Config {
    
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    
}
