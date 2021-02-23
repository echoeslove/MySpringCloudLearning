package pers.benj.demo.api.controller;

import com.netflix.hystrix.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.benj.demo.domain.entity.Person;
import pers.benj.demo.domain.repository.DemoRepository;

@RestController
@RequestMapping("/service1")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoRepository demoRepository;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from service-1 : " + Thread.currentThread();
    }

    /**
     * get max element
     * 
     * @author benjamin
     * @date 2020/10/30 3:42 PM
     * @return Person
     */
    public Person getMaxPerson() {
        return demoRepository.getMaxAgePerson();
    }

    @GetMapping("/test/hystrix")
    public String testHystrix(@RequestParam("times")int times) {
        HystrixCommand<String> command = new DemoCommand("testCircuitBreaker");
        String result = "Thread: " + Thread.currentThread() + "  isCircuitBreakerOpen: "
                        + command.isCircuitBreakerOpen() + "  result: " + command.execute() + " " + times;
        logger.info(result);
        return result;
    }
}
