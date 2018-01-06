package com.example.denis.holodos.modules;

import java.util.HashMap;
import java.util.Map;

public class Group /*extends Model*/ {

    private static final long serialVersionUID = 1L;

    private String title;

    private Map<Long, User> users = new HashMap<>();

    public Group() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<Long, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Long, User> users) {
        this.users = users;
    }
}