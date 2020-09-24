package com.contamov.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.contamov.model" })
@ComponentScan(basePackages = { "com.*" })
@EnableJpaRepositories(basePackages = { "com.contamov.repository" })
public class ContaMovApplication {

  public static void main(String[] args) {
    SpringApplication.run(ContaMovApplication.class, args);
  }
  
}