package pers.benj.servicefirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("pers.benj")
@SpringBootConfiguration
public class ServiceFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFirstApplication.class, args);
    }

}
