package pers.benj.servicesecond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("pers.benj")
@SpringBootConfiguration
public class ServiceSecondApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSecondApplication.class, args);
    }

}
