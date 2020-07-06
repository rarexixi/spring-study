package org.xi.zk.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.xi.zk.register.ServiceRegister;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

    @Autowired
    ServiceRegister serviceRegister;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent){
        serviceRegister.register();
    }
}
