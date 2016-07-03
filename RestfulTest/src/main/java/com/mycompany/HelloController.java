package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by rociomac on 2/07/2016.
 * Studying Spring Boot
 */

@EnableAutoConfiguration
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("")
    @ResponseBody
    public String hello(){
        return "Hello World!";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public String greetings(@RequestBody String name){
        return "Hola " + name;
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloController.class, args);
    }
}
