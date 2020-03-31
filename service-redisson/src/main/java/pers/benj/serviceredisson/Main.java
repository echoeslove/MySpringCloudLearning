package pers.benj.serviceredisson;

import java.util.BitSet;

import org.redisson.api.RBitSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Main {

    @Autowired
    private RedissonClient redissonClient;

    public void test(){
        BitSet bitSet = new BitSet();
        bitSet.set(1,true);
        RBitSet set =redissonClient.getBitSet("s");
        set.set(0,true);
    }
}
