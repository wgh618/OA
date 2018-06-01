package com.my.oa.system.domain;

import java.io.Serializable;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public class UserRole implements Serializable {
    private Integer id; //ID
    private User user; //员工
    private Role role; //岗位

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
