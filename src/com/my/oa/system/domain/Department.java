package com.my.oa.system.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 * 部门
 */
public class Department implements Serializable {
    private Integer id; //ID
    private String name; //部门名字
    private String description; //描述

    private Set<User> users = new HashSet<User>(); //该部门所拥有的的员工
    private Department parent; //上级部门
    private Set<Department> children = new HashSet<Department>(); //下级部门

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }

    public Set<Department> getChildren() {
        return children;
    }

    public void setChildren(Set<Department> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
