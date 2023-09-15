package com.example.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String helloController() {
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
    }

    public String error() {
        return "error";
    }
}
