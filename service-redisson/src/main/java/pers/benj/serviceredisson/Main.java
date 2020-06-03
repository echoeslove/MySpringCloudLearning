package pers.benj.serviceredisson;

import java.util.BitSet;

import org.redisson.api.RBitSet;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    @Autowired
    private RedissonClient redissonClient;

    public void test() {
        BitSet bitSet = new BitSet();
        bitSet.set(1, true);
        RBitSet set = redissonClient.getBitSet("s");

        set.set(0, true);
    }

    public static void main(String[] args) {
        long t = System.currentTimeMillis();

        log();

        long t2 = System.currentTimeMillis();

        logger.info("time:" + (t2 - t));
    }

    private static void log() {
        logger.debug("fun debug");
    }
}
