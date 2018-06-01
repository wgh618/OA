package com.my.oa.system.domain;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 * 权限
 */
public class Privilege implements Serializable{
    private Integer id;
    private String name;
    private String url;

    private Privilege parent;

    Set<Privilege> children;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Privilege getParent() {
        return parent;
    }

    public void setParent(Privilege parent) {
        this.parent = parent;
    }

    public Set<Privilege> getChildren() {
        return children;
    }

    public void setChildren(Set<Privilege> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "PrivilegeInterceptor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Privilege privilege = (Privilege) o;

        if (id != null ? !id.equals(privilege.id) : privilege.id != null)
            return false;
        if (name != null ? !name.equals(privilege.name) : privilege.name != null)
            return false;
        if (url != null ? !url.equals(privilege.url) : privilege.url != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
