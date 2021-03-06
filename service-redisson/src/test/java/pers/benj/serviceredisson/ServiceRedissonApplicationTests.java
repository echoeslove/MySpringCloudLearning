package pers.benj.serviceredisson;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

import pers.benj.User;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceRedissonApplicationTests {

    @Test
    void contextLoads() {}

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;



//    @Test
//    public void test() {
//        RBitSet set = redissonClient.getBitSet("s");
//        set.set(0, true);
//        set.set(1, true);
//        set.set(2, false);
//        set.set(3, true);
//
//        System.out.println(set.toString());
//    }

    @Test
    public void test2() {
        String key = "active:" + LocalDate.now().toString();

        redisTemplate.execute(
                        (RedisCallback<Boolean>) redisConnection -> redisConnection.setBit(key.getBytes(), 0L, true));
        redisTemplate.execute(
                        (RedisCallback<Boolean>) redisConnection -> redisConnection.setBit(key.getBytes(), 1L, true));
        redisTemplate.execute(
                        (RedisCallback<Boolean>) redisConnection -> redisConnection.setBit(key.getBytes(), 2L, true));
        redisTemplate.execute(
                        (RedisCallback<Boolean>) redisConnection -> redisConnection.setBit(key.getBytes(), 2L, false));

        Long count = (Long) redisTemplate
                        .execute((RedisCallback<Long>) redisConnection -> redisConnection.bitCount(key.getBytes()));
        System.out.println(count);
    }

    @Test
    public void test3() {
        redissonClient.getFairLock("AAAA");

        String key = "t_user:user_id:2";
        RMap<String, String> map = redissonClient.getMap(key);
//        User user = new User("benj");
//        map.put(String.valueOf(1), JSONObject.toJSONString(user));
    }

    @Test
    public void test99() {
        CustomFunc customFunc = new CustomFunc(true, redissonClient);
        CustomFunc customFunc2 = new CustomFunc(false, redissonClient);

        Thread t1 = new Thread(customFunc);
        t1.setName("t1");
        t1.start();
        System.out.println("start t1");

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(customFunc2);
        t2.setName("t2");
        t2.start();
        System.out.println("start t2");

        while (true) {

        }
    }

    @Test
    public void test4() {
        String key = "t_user:user_id:2";
        User user = new User("tom");

        BeanMap beanMap1 = BeanMap.create(user);

        redisTemplate.opsForHash().putAll(key, beanMap1);

        // redisTemplate.opsForHash().put(key, "2", JSONObject.toJSONString(user));
    }

    @Test
    public void test5() {
        String key = "t_user:user_id";

        List<String> result = new ArrayList<>();

        try {
            Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(key,
                            ScanOptions.scanOptions().match("*").count(1000).build());
            while (cursor.hasNext()) {
                Object key1 = cursor.next().getKey();
                Object valueSet = cursor.next().getValue();

                String res = key1 + "-" + valueSet;
                result.add(res);
            }

            for (String re : result) {
                System.out.println(re);
            }
            cursor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6() {
        String key = "t_user:user_id:*";


        Set<String> keys = (Set<String>) redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keysTmp = new HashSet<>();
            Cursor<byte[]> cursor =
                            connection.scan(new ScanOptions.ScanOptionsBuilder().match(key).count(1000).build());
            while (cursor.hasNext()) {
                keysTmp.add(new String(cursor.next()));
            }
            try {
                cursor.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }

            return keysTmp;

        });

        System.out.println(keys);


    }

//    @Test
//    public void testLock() {
//        RLock fairLock = redissonClient.getFairLock("FairLock");
//        if (!fairLock.tryLock()) {
//            System.out.println("other is used");
//            return;
//        }
//        fairLock.lock();
//
//        System.out.println("Function1 execute");
//
//        try {
//            TimeUnit.SECONDS.sleep(30);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            fairLock.unlock();
//        }
//
//    }
//
//    @Test
//    public void testLock2() {
//        RLock fairLock = redissonClient.getFairLock("FairLock");
//        if (!fairLock.tryLock()) {
//            System.out.println("other is used");
//            return;
//        }
//        fairLock.lock();
//
//        System.out.println("Function2 execute");
//
//        fairLock.unlock();
//    }
}
