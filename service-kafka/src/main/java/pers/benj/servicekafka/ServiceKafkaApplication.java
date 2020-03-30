package pers.benj.servicekafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@SpringBootConfiguration
@ComponentScan("pers.benj")
public class ServiceKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceKafkaApplication.class, args);
    }

}
