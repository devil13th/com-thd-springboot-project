package com.thd.processmanagementserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.stream.Stream;

/**
 * com.thd.processmanagementserver.Application
 *
 * @author: wanglei62
 * @DATE: 2021/1/25 14:40
 **/
@SpringBootApplication(
        scanBasePackages = "com.thd",
        exclude={org.activiti.spring.boot.SecurityAutoConfiguration.class}
)
public class Application extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    public static void main(String[] args) {
        //System.setProperty("spring.devtools.restart.enabled", "false");
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        String[] names = ctx.getBeanDefinitionNames();
        Stream.of(names).forEach(System.out::println);

    }


}