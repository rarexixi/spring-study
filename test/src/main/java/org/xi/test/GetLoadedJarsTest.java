package org.xi.test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

public class GetLoadedJarsTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        // 第一种方式，不会将当前jar包加载到当前classpath
        // URL url = new URL("file:///Users/xi/.m2/repository/mysql/mysql-connector-java/8.0.24/mysql-connector-java-8.0.24.jar");
        // URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { url }, GetLoadedJarsTest.class.getClassLoader());
        // Class<?> driverClass = urlClassLoader.loadClass("com.mysql.cj.jdbc.Driver");
        // Class<?> driverClass = Class.forName("com.mysql.cj.jdbc.Driver", true, urlClassLoader);
        // Class<?> driverClass = Class.forName("com.mysql.cj.jdbc.Driver"); // 无法获取


        // 第二种方式，会将当前jar包加载到当前classpath
        URL url = new URL("file:///Users/xi/.m2/repository/mysql/mysql-connector-java/8.0.24/mysql-connector-java-8.0.24.jar");
        URLClassLoader urlClassLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        method.setAccessible(true);
        method.invoke(urlClassLoader, url);

        Class<?> driverClazz = Class.forName("com.mysql.cj.jdbc.Driver");

        ClassLoader classLoader = GetLoadedJarsTest.class.getClassLoader();
        Enumeration<URL> paths = classLoader.getResources("META-INF");
        while (paths.hasMoreElements()) {
            String path = paths.nextElement().toString();
            if (!path.contains("jdk")) {
                System.out.println(path);
            }
        }
    }
}
