package com.my.oa.workflow.service.impl;

import com.my.oa.workflow.service.ProcessDefinitionService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Service("processDefinitionService")
@Transactional
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {

    @Resource(name = "processEngine")
    private ProcessEngine processEngine;

    @Override
    public String findProcessDefinitionNameByKey(String key) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("");
        }
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition processDefinition = query.processDefinitionKey(key).latestVersion().singleResult();
        return processDefinition != null ? processDefinition.getName() : null;
    }

    @Override
    public List<ProcessDefinition> findAllProcessDefinitions() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        return query.latestVersion().list();
    }
}
