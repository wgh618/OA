package com.my.oa.workflow.domain;

import java.io.Serializable;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 * 申请模板
 */
public class Template implements Serializable {
    private Integer id;
    private String name;
    private String processDefinitionKey;
    private String path;

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

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", processDefinitionKey='" + processDefinitionKey + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
