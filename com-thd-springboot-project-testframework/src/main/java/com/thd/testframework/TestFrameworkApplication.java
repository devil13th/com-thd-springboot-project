package com.thd.testframework;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.stream.Stream;

/**
 * com.thd.springboot.project.note.NoteApplication
 *
 * @author: wanglei62
 * @DATE: 2021/3/26 17:09
 **/
@SpringBootApplication(scanBasePackages = "com.thd.*")
@EnableSwagger2Doc
@EnableSwagger2
public class TestFrameworkApplication {
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }


    public static void main(String[] args) {
        //System.setProperty("spring.devtools.restart.enabled", "false");
        ConfigurableApplicationContext ctx = SpringApplication.run(TestFrameworkApplication.class, args);
        String[] names = ctx.getBeanDefinitionNames();
        Stream.of(names).forEach(System.out::println);
    }

}
