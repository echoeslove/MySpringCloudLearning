package pers.benj.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service1")
public class DemoController {

    @GetMapping("/hello")
    public String sayHello() {
        Map<String,String> map = new HashMap<>();
        return "Hello from service-1";
    }
}
