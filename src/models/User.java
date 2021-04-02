package models;

import common.utils.Authorization;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class User implements Serializable {
    private String id;
    private String password;
    private Authorization authorization;

    public User(String id, String password,Authorization authorization) {
        this.id = id;
        this.password = password;
        this.authorization=authorization;
    }

    public String getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }

    public Authorization getAuthorization() {
        return authorization;
    }
}
