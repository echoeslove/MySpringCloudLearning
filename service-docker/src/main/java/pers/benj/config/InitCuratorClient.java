package pers.benj.config;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InitCuratorClient implements InitializingBean {

    private static final String PATH = "/leader/example";

    @Value("${spring.application.name}")
    private String name;

    @Autowired
    private CuratorFramework curatorFramework;

    @Override
    public void afterPropertiesSet() throws Exception {
        CuratorSelector curatorSelector = new CuratorSelector(curatorFramework, PATH, name);

        curatorSelector.start();
    }
}
