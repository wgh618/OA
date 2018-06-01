package com.my.oa.workflow.service;

import com.my.oa.system.domain.User;
import com.my.oa.workflow.domain.Application;
import com.my.oa.workflow.domain.Approve;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface ApproveService {

    /**
     * 查询即将我要审批的申请
     * @param approver
     * @return
     */
    public List<Application> findApplicationsByApproving(User approver);

    /**
     * 审批
     * @param approve
     */
    public void approve(Approve approve);
}
