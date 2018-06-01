package com.my.oa.forum.domain;

import com.my.oa.system.domain.User;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public class Reply implements Serializable {

    private Integer id; //ID
    private String content; //内容
    private String faceIcon; //表情
    private Date postTime; //回复时间
    private String ipAddr; //IP地址
    private User author; //作者

    private Topic topic;// 所属的主题

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFaceIcon() {
        return faceIcon;
    }

    public void setFaceIcon(String faceIcon) {
        this.faceIcon = faceIcon;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", faceIcon='" + faceIcon + '\'' +
                ", postTime=" + postTime +
                ", ipAddr='" + ipAddr + '\'' +
                ", author=" + author +
                '}';
    }
}
