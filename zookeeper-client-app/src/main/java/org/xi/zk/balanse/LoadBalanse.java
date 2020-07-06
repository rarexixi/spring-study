package org.xi.zk.balanse;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

public class LoadBalanse {

    public volatile static List<String> SERVICE_LIST;

    public String choseServiceHost() {
        String result = "";

        if (!CollectionUtils.isEmpty(SERVICE_LIST)) {
            int index = new Random().nextInt(SERVICE_LIST.size());
            result = SERVICE_LIST.get(index);
        }
        return result;
    }
}