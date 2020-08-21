package pers.benj.serviceredisson;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

public class CustomFunc implements Runnable {

    public boolean wait = false;

    public RedissonClient client;

    public CustomFunc(boolean wait, RedissonClient client) {
        this.wait = wait;
        this.client = client;
    }

    public void fund(boolean wait) {
        String thread = Thread.currentThread().getName();
//        System.out.println(thread + " before - try lock");
        RLock lock = client.getFairLock("AAAA");
        lock.lock();

        System.out.println(thread + " after - get lock");
        System.out.println(thread + " wait");

        String key = "t_user:user_id:2";
        RMap<String, String> map = client.getMap(key);
        System.out.println(thread + " - " + map.entrySet().size());

        if (wait) {
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lock.unlock();
        System.out.println(thread + " - unlock");

        System.out.println(thread + " - " + "finish");
    }

    @Override
    public void run() {
        fund(wait);
    }
}
