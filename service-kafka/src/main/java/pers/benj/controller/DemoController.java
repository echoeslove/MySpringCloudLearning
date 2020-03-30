package pers.benj.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.benj.servicekafka.IndicatorService;

@RestController
@RequestMapping("kafka-service")
public class DemoController {

    @Autowired
    private IndicatorService indicatorService;

    @PostMapping("/send/message")
    public String sendMessage(@RequestParam String message) {
        indicatorService.sendMessage("Test", message + " test: " + LocalDateTime.now());
        return message;
    }

}
