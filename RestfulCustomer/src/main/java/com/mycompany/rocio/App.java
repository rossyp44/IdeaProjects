package com.mycompany.rocio;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by rociomac on 2/07/2016.
 * Studying Spring Boot
 */

@EnableAutoConfiguration
@ComponentScan("com.mycompany.rocio")
@EnableSwagger
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }
}
