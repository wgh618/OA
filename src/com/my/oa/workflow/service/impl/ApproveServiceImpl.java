package com.my.oa.workflow.service.impl;

import com.my.oa.system.domain.User;
import com.my.oa.workflow.domain.Application;
import com.my.oa.workflow.domain.Approve;
import com.my.oa.workflow.mapper.ApproveMapper;
import com.my.oa.workflow.service.ApplicationService;
import com.my.oa.workflow.service.ApproveService;
import com.my.oa.workflow.service.WorkFlowService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Service("approveService")
@Transactional
public class ApproveServiceImpl implements ApproveService {

    @Autowired
    private WorkFlowService workFlowService;

    @Autowired
    private ApproveMapper approveMapper;

    @Autowired
    private ApplicationService applicationService;

    @Override
    public List<Application> findApplicationsByApproving(User approver) {
        Application application = null;
        List<Application> applicationList = new ArrayList<Application>();

        List<Task> taskList = workFlowService.findTasksByAssignee(approver.getLoginName());

        for (Task task : taskList) {
            application = workFlowService.findApplicationByTask(task);

            applicationList.add(application);
        }

        return applicationList;
    }

    @Override
    public void approve(Approve approve) {
        if (approve == null) {
            throw new IllegalArgumentException("");
        }

        Application application = approve.getApplication();
        User approver = approve.getApprover();
        String isPassed = approve.getIsPassed();

        if (application == null) {
            throw new RuntimeException("");
        }

        // 插入审批对象
        this.approveMapper.insert(approve);

        // 根据审批的申请查询出流程实例
        ProcessInstance processInstance = workFlowService.findProcessInstanceByAppliation(application);

        Task task = workFlowService.findTaskByProcessInstance(processInstance, approver);

        // 办理任务
        workFlowService.completeTask(task);

        ProcessInstance pi = workFlowService.findProcessInstanceByAppliation(application);
        if ("同意".equals(isPassed)) {
            if (pi == null) {
                this.applicationService.modifyApplicationStatus(application, Application.STATUS_APPROVED);
            }
        } else if ("不同意".equals(isPassed)) {
            if (pi != null) {
                workFlowService.deleteProcessInstance(pi);
            }

            this.applicationService.modifyApplicationStatus(application, Application.STATUS_REJECTED);
        }
    }
}
