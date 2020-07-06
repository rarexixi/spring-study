package org.xi.zk.register;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Slf4j
@Service
public class ServiceRegister {

    private static final String BASE_SERVICE = "/zookeeper";

    private static final String SERVICE_NAME = "/server";

    @Autowired
    ServerProperties serverProperties;

    public void register() {
        String path = BASE_SERVICE + SERVICE_NAME;
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, null);
            Stat exists = zooKeeper.exists(BASE_SERVICE + SERVICE_NAME, false);
            if (exists == null) {
                zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            String server_path = InetAddress.getLocalHost().getHostAddress() + ":" + serverProperties.getPort();
            zooKeeper.create(path + "/child", server_path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            log.info("产品服务注册成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}