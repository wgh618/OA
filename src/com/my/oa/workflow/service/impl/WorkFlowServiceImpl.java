package com.my.oa.workflow.service.impl;

import com.my.oa.system.domain.User;
import com.my.oa.workflow.domain.Application;
import com.my.oa.workflow.domain.Template;
import com.my.oa.workflow.service.WorkFlowService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/18.
 */
@Service
@Transactional
public class WorkFlowServiceImpl implements WorkFlowService {

    @Autowired
    private ProcessEngine processEngine;

    /**
     * 启动工作流
     * 启动流程实例 + 办理提交申请 + 流程变量
     *
     * @param application
     */
    @Override
    public void startWorkFlow(Application application, String manager) {
        String taskId = null;

        if (application == null) {
            throw new IllegalArgumentException("");
        }

        User applicant = application.getApplicant();
        Template template = application.getTemplate();

        // 启动流程实例
        RuntimeService runtimeService = this.processEngine.getRuntimeService();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("application", application);
        variables.put("applicationId", application.getId());
        variables.put("manager", manager);

        // 启动流程实例，同时挂了一个流程变量，实际上流程流入到了提交申请，此时提交申请设置了#{application.applicant.name}
        // 部门经理设置 #{manager}
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(template.getProcessDefinitionKey(), variables);

        // 办理任务
        TaskService taskService = this.processEngine.getTaskService();
        TaskQuery query = taskService.createTaskQuery();
        Task task = query.processInstanceId(processInstance.getId()).taskAssignee(applicant.getLoginName())
                .singleResult();
        taskService.complete(task.getId());
    }

    @Override
    public List<Task> findTasksByAssignee(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("");
        }

        TaskService taskService = this.processEngine.getTaskService();
        TaskQuery query = taskService.createTaskQuery();
        return query.taskAssignee(name).list();
    }

    @Override
    public ProcessInstance findProcessInstanceByTask(Task task) {

        if (task == null) {
            throw new IllegalArgumentException("");
        }

        RuntimeService runtimeService = this.processEngine.getRuntimeService();
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        return query.processInstanceId(task.getProcessInstanceId()).singleResult();
    }

    @Override
    public Application findApplicationByTask(Task task) {
        TaskService taskService = this.processEngine.getTaskService();
        return (Application) taskService.getVariable(task.getId(), "application");
    }

    @Override
    public ProcessInstance findProcessInstanceByAppliation(Application application) {
        if (application == null) {
            throw new IllegalArgumentException("");
        }

        RuntimeService runtimeService = this.processEngine.getRuntimeService();
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        return query.variableValueEquals("applicationId", application.getId()).singleResult();
    }

    @Override
    public Task findTaskByProcessInstance(ProcessInstance processInstance, User approver) {
        if (processInstance == null) {
            throw new IllegalArgumentException("");
        }

        if (approver == null) {
            throw new IllegalArgumentException("");
        }

        TaskService taskService = this.processEngine.getTaskService();
        TaskQuery query = taskService.createTaskQuery();
        return query.taskAssignee(approver.getLoginName()).processInstanceId(processInstance.getId()).singleResult();
    }

    @Override
    public void completeTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("");
        }

        TaskService taskService = this.processEngine.getTaskService();
        taskService.complete(task.getId());
    }

    @Override
    public void deleteProcessInstance(ProcessInstance processInstance) {
        if (processInstance == null) {
            throw new IllegalArgumentException("");
        }

        RuntimeService runtimeService = this.processEngine.getRuntimeService();
        runtimeService.deleteProcessInstance(processInstance.getId(), "true");
    }
}
