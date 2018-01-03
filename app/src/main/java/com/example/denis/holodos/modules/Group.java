package com.example.denis.holodos.modules;

import java.util.HashMap;
import java.util.Map;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@Table(name = "groups")
public class Group extends Model {

    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    private String title;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Map<Long, User> users = new HashMap<>();

    public Group() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    @JsonbTransient
    public Map<Long, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Long, User> users) {
        this.users = users;
    }
}