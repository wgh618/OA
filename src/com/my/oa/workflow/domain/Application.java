package com.my.oa.workflow.domain;

import com.my.oa.system.domain.User;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 * 申请
 */
public class Application implements Serializable {

    /** 状态常量：审批中 */
    public static final String STATUS_RUNNING = "审批中";

    /** 状态常量：已通过 */
    public static final String STATUS_APPROVED = "已通过";

    /** 状态常量：未通过 */
    public static final String STATUS_REJECTED = "未通过";

    private Integer id;
    private Template template;// 所使用的申请模板
    private Set<Approve> approveInfos = new HashSet<Approve>();
    private User applicant;// 申请人

    private String title;// 标题
    private Date applyTime;// 申请时间
    private String path;// 文档的存储路径
    private String status; // 当前的状态

    public static String getStatusRunning() {
        return STATUS_RUNNING;
    }

    public static String getStatusApproved() {
        return STATUS_APPROVED;
    }

    public static String getStatusRejected() {
        return STATUS_REJECTED;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Set<Approve> getApproveInfos() {
        return approveInfos;
    }

    public void setApproveInfos(Set<Approve> approveInfos) {
        this.approveInfos = approveInfos;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
