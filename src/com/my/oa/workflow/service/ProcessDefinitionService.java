package com.my.oa.workflow.service;

import org.activiti.engine.repository.ProcessDefinition;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface ProcessDefinitionService {
    public String findProcessDefinitionNameByKey(String key);

    public List<ProcessDefinition> findAllProcessDefinitions();
}
