package pers.benj.demo.api.controller;

import java.util.Random;

import com.netflix.hystrix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoCommand extends HystrixCommand<String> {

    Logger logger = LoggerFactory.getLogger(DemoCommand.class);


    public DemoCommand(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ThreadPoolTestGroup"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true)// 默认是true，本例中为了展现该参数
                                        .withCircuitBreakerForceOpen(false)// 默认是false，本例中为了展现该参数
                                        .withCircuitBreakerForceClosed(false)// 默认是false，本例中为了展现该参数
                                        .withCircuitBreakerErrorThresholdPercentage(1)// (1)错误百分比超过5%
                                        .withCircuitBreakerRequestVolumeThreshold(1)// (2)10s以内调用次数10次，同时满足(1)(2)熔断器打开
                                        .withCircuitBreakerSleepWindowInMilliseconds(5000)// 隔5s之后，熔断器会尝试半开(关闭)，重新放进来请求
                        // .withExecutionTimeoutInMilliseconds(1000)
                        ).andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withMaxQueueSize(10) // 配置队列大小
                                        .withCoreSize(2) // 配置线程池里的线程数
                        ));
    }

    @Override
    protected String run() throws Exception {
        // send request

        Random rand = new Random();
        // 模拟错误百分比(方式比较粗鲁但可以证明问题)
        if (1 == rand.nextInt(2)) {
            logger.error(String.valueOf(this.isCircuitBreakerOpen()));
            throw new Exception("error");
        }


        return "success: ";
    }

    @Override
    protected String getFallback() {
        return "fallback: ";
    }
}
