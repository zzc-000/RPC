package com.zzc.controller;


import com.zzc.service.BasicService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
@Configuration
@ComponentScan("\\")
public class BasicController {
     public static void main(String[] args) throws InterruptedException {
         ApplicationContext context =
                 new AnnotationConfigApplicationContext(BasicController.class);
        BasicService basicService = context.getBean(BasicService.class);
        basicService.testSaveUser();
     }

}
