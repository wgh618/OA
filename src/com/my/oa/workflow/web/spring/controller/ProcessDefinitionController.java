package com.my.oa.workflow.web.spring.controller;

import com.my.oa.workflow.service.ProcessDefinitionService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.collections.Predicate;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */

@Controller
@RequestMapping("/processDefinition")
public class ProcessDefinitionController {

    @Resource(name = "processEngine")
    private ProcessEngine processEngine;
    /**
     * 列表
     */

    @RequestMapping("/list")
    public String list(Model model) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitionList = query.latestVersion().list();
        model.addAttribute("processDefinitionList", processDefinitionList);
        return "processDefinition/list";
    }


    /**
     * 部署页面
     */

    @RequestMapping(value = "/deployUI", method = RequestMethod.GET)
    public String deploy() {
        return "processDefinition/deployUI";
    }


    /**
     * 部署
     */

    @RequestMapping(value = "/deploy", method = RequestMethod.POST)
    public String deploy(MultipartFile resource, HttpServletRequest request) {
        FileInputStream in = null;

        try {
            String path = request.getSession().getServletContext().getRealPath("/process");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }

            String originalFilename = resource.getOriginalFilename();
            File resultFile = new File(file, originalFilename);
            FileUtils.copyInputStreamToFile(resource.getInputStream(), resultFile);

            RepositoryService repositoryService = processEngine.getRepositoryService();
            DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
            deploymentBuilder.addZipInputStream(new ZipInputStream(new FileInputStream(resultFile)));
            deploymentBuilder.deploy();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/processDefinition/list";
    }


    /**
     * 删除
     */

    @RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
    public String delete(@PathVariable("key") String key) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitionList = query.processDefinitionKey(key).list();
        if (!CollectionUtils.isEmpty(processDefinitionList)) {
            for (ProcessDefinition processDefinition : processDefinitionList) {
                // 删除部署（删除流程定义）
                repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
            }
        }

        return "redirect:/processDefinition/list";
    }


    /**
     * 查看流程图
     */

    @RequestMapping(value = "/showProcessImage/{id}", method = RequestMethod.GET)
    public String showProcessImage(@PathVariable("id") String processDefinitionId, HttpServletResponse response) {
        InputStream inputStream = null;
        ServletOutputStream outputStream = null;
        byte[] buffer = null;
        int i = 0;

        try {
            response.setContentType("image/png");
            outputStream = response.getOutputStream();

            RepositoryService repositoryService = processEngine.getRepositoryService();
            ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

//            processDefinitionId = processDefinitionId.replaceAll("-",":");

            ProcessDefinition processDefinition = query.processDefinitionId(processDefinitionId).singleResult();
            String deploymentId = processDefinition.getDeploymentId();

            List<String> deploymentResourceNameList = repositoryService.getDeploymentResourceNames(deploymentId);

            org.apache.commons.collections.CollectionUtils.filter(deploymentResourceNameList, new Predicate() {

                @Override
                public boolean evaluate(Object obj) {
                    String temp = (String) obj;
                    return temp.endsWith(".png");
                }
            });

            String fileName = deploymentResourceNameList.get(0);
            inputStream = repositoryService.getProcessDiagram(processDefinitionId);
            buffer = new byte[inputStream.available()];

            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

