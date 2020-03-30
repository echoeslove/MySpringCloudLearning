package pers.benj.serviceredison;

import java.util.BitSet;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBitSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceRedisonApplicationTests {

    @Test
    void contextLoads() {}

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test() {
        BitSet bitSet = new BitSet();
        bitSet.set(1, true);
        RBitSet set = redissonClient.getBitSet("s");
        set.set(0, true);

    }

}
