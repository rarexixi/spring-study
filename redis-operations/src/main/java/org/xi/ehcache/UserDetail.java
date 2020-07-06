package org.xi.ehcache;

import java.io.Serializable;

public class UserDetail implements Serializable {

    public UserDetail() {
    }

    public UserDetail(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
