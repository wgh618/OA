package com.my.oa.workflow.domain;

import com.my.oa.system.domain.User;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 * 审批
 */
public class Approve implements Serializable {

    private Integer id;
    private Date approveDate;
    private String isPassed;
    private String suggestion;
    private User approver;

    private Application application;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(String isPassed) {
        this.isPassed = isPassed;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
