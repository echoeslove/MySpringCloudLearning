package pers.benj.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service2")
public class DemoController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from service-2";
    }
}
