package pers.benj.serviceredisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String nodes;

    @Bean
    public RedissonClient getRedisson() {
        String[] split = nodes.split(",");
        Config config = new Config();
        for (String node : split) {
            config.useClusterServers().addNodeAddress("redis://" + node).setMasterConnectionPoolSize(64)
                            .setMasterConnectionMinimumIdleSize(24).setSlaveConnectionPoolSize(64)
                            .setSlaveConnectionMinimumIdleSize(24).setRetryAttempts(3).setRetryInterval(1500)
                            .setReadMode(ReadMode.SLAVE).setTimeout(10000);
        }
        config.setNettyThreads(32);
        config.setThreads(16);
        return Redisson.create(config);
    }
}
