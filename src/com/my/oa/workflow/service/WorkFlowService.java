package com.my.oa.workflow.service;

import com.my.oa.system.domain.User;
import com.my.oa.workflow.domain.Application;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface WorkFlowService {

    public void startWorkFlow(Application application, String manager);

    public List<Task> findTasksByAssignee(String name);

    public ProcessInstance findProcessInstanceByTask(Task task);

    public Application findApplicationByTask(Task task);

    public ProcessInstance findProcessInstanceByAppliation(Application application);

    public Task findTaskByProcessInstance(ProcessInstance processInstance, User approver);

    public void completeTask(Task task);

    public void deleteProcessInstance(ProcessInstance processInstance);
}
