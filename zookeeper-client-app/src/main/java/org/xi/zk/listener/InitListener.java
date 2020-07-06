package org.xi.zk.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.xi.zk.balanse.LoadBalanse;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebListener
@Slf4j
public class InitListener implements ServletContextListener {

    private static final String BASE_SERVICE = "/zookeeper";

    private static final String SERVICE_NAME = "/server";

    private ZooKeeper zooKeeper;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    public void init() {
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, watchedEvent -> {
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged && watchedEvent.getPath().equals(BASE_SERVICE + SERVICE_NAME)) {
                    updateServerList();
                }
            });
            updateServerList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateServerList() {
        List<String> serverList = new ArrayList<>();
        try {
            String basePath = BASE_SERVICE + SERVICE_NAME;
//            byte[] data1 = zooKeeper.getData(basePath, false, null);
//            String host1 = new String(data1, "utf-8");
            List<String> children = zooKeeper.getChildren(basePath, true);
            for (String subNode : children) {
                String path = basePath + "/" + subNode;
                byte[] data = zooKeeper.getData(path, false, null);
                String host = new String(data, "utf-8");
                log.info("host：" + host);
                serverList.add(host);
            }
            LoadBalanse.SERVICE_LIST = serverList;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
