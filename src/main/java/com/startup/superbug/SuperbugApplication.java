package com.startup.superbug;

import com.startup.superbug.config.DateConfig;
import com.startup.superbug.config.FileStorageProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import javafx.application.Application;

@EnableAutoConfiguration
@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class SuperbugApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(SuperbugApplication.class, args);
        System.out.println("Superbug is running...!");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(DateConfig.class);
        ctx.refresh();
        ctx.close();
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.println("Environment: \t");
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }
    }
}
