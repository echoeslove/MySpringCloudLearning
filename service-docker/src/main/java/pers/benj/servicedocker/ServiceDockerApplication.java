package pers.benj.servicedocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pers.benj")
public class ServiceDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDockerApplication.class, args);
	}

}
