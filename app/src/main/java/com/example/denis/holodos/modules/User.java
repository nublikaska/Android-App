package com.example.denis.holodos.modules;

import java.util.ArrayList;
import java.util.List;

public class User /*extends Model*/ {

    private static final long serialVersionUID = 1L;

    private String login;

    private String password;

    private Group group;

    private List<Check> checks = new ArrayList<>();

    public User() {

    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Check> getChecks() {
        return checks;
    }

    public void setChecks(List<Check> checks) {
        this.checks = checks;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
