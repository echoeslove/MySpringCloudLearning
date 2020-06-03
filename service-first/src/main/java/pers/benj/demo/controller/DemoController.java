package pers.benj.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service1")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    public static final ThreadLocal<String> STRING_THREAD_LOCAL = new ThreadLocal<>();
    public static final ThreadLocal<Integer> INTEGER_THREAD_LOCAL = new ThreadLocal<>();

    @GetMapping("/hello")
    public String sayHello() {
        String s = "Hello from service-1 : " + Thread.currentThread();
        logger.debug(s);


        if (INTEGER_THREAD_LOCAL.get() == null) {
            INTEGER_THREAD_LOCAL.set(0);
        } else {
            int i = INTEGER_THREAD_LOCAL.get();
            i++;
            INTEGER_THREAD_LOCAL.set(i);
        }
        STRING_THREAD_LOCAL.set(Thread.currentThread().getName() + " :" + INTEGER_THREAD_LOCAL.get());
        logger.debug(STRING_THREAD_LOCAL.get() + INTEGER_THREAD_LOCAL.get());

        STRING_THREAD_LOCAL.remove();
        INTEGER_THREAD_LOCAL.remove();

        return s;
    }
}
