package com.my.oa.system.domain;

import java.io.Serializable;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 * 岗位-权限中间表
 */
public class RolePrivilege implements Serializable {
    private Integer id;
    private Role role;
    private Privilege privilege;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}
