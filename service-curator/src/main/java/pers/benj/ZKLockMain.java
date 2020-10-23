package pers.benj;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

public class ZKLockMain {

    public static void main(String[] args) {

        Thread t1 = new Thread(new LockThread(), "t1");
        Thread t2 = new Thread(new LockThread(), "t2");

        t1.start();
        t2.start();
    }

    public static class LockThread implements Runnable {
        public void run() {
            final String connectStr = "localhost:2181,localhost:2182,localhost:2183";

            // 重试策略，初始化每次重试之间需要等待的时间，基准等待时间为1秒。
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

            // 使用默认的会话时间（60秒）和连接超时时间（15秒）来创建 Zookeeper 客户端
            CuratorFramework client = CuratorFrameworkFactory.builder().connectString(connectStr)
                            .connectionTimeoutMs(15 * 1000).sessionTimeoutMs(60 * 100).retryPolicy(retryPolicy).build();

            // 启动客户端
            client.start();

            final String lockNode = "/lock_node";
            InterProcessMutex lock = new InterProcessMutex(client, lockNode);
            try {
                // 1. Acquire the mutex - blocking until it's available.
                lock.acquire();

                // OR

                // 2. Acquire the mutex - blocks until it's available or the given time expires.
                if (lock.acquire(30, TimeUnit.SECONDS)) {
                    Stat stat = client.checkExists().forPath(lockNode);
                    if (null != stat) {
                        // Dot the transaction

                        System.out.println(Thread.currentThread().getName() + " get lock");

                        TimeUnit.SECONDS.sleep(10L);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (lock.isAcquiredInThisProcess()) {
                    try {
                        lock.release();
                        System.out.println(Thread.currentThread().getName() + " release lock");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            client.close();
        }
    }
}
