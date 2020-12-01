package pers.benj.config;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

public class CuratorSelector extends LeaderSelectorListenerAdapter implements Closeable {

    private final String name;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired
    private final LeaderSelector leaderSelector;

    public CuratorSelector(CuratorFramework curatorFramework, String path, String name) {
        this.name = name;
        this.leaderSelector = new LeaderSelector(curatorFramework, path, this);
        leaderSelector.autoRequeue();
    }

    @Override
    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
        System.out.println(this.name + " has become leader");

        this.countDownLatch.await();
    }

    public void start() throws IOException {
        leaderSelector.start();
    }

    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }
}
