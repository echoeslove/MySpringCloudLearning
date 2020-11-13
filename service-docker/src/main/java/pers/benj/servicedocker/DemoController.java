package pers.benj.servicedocker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docker-service")
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger("service-docker");

    @GetMapping("/hello")
    public String sayHello() {
        logger.info("get request");
        return "Hello from docker service : " + Thread.currentThread();
    }
}
