package com.my.oa.workflow.web.spring.controller;

import com.my.oa.core.util.FileUploadUtils;
import com.my.oa.workflow.domain.Template;
import com.my.oa.workflow.service.ProcessDefinitionService;
import com.my.oa.workflow.service.TemplateService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Controller
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ProcessDefinitionService processDefinitionService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Template> templateList = this.templateService.findAllTemplates();
        model.addAttribute("templateList", templateList);
        return "template/list";
    }

    @RequestMapping(value = "/addUI", method = RequestMethod.GET)
    public String add(Model model) {
        List<ProcessDefinition> processDefinitionList = processDefinitionService.findAllProcessDefinitions();
        model.addAttribute("processDefinitionList", processDefinitionList);
        return "template/saveUI";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(MultipartFile resource, HttpServletRequest request, Template template) {
        try {
            File uploadFile = FileUploadUtils.uploadFile(resource, request);
            template.setPath(uploadFile.getAbsolutePath());
            templateService.addTemplate(template);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/template/list";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id) {
        Integer _id = null;
        if (StringUtils.isNotBlank(id)) {
            _id = Integer.parseInt(id);
        }
        Template template = this.templateService.findTemplateById(_id);

        File file = new File(template.getPath());
        if (file.exists()) {
            file.delete();
        }

        this.templateService.deleteTemplate(template);

        return "redirect:/template/list";
    }

    @RequestMapping(value = "/editUI/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable String id) {
        Integer _id = null;

        if (StringUtils.isNotBlank(id)) {
            _id = Integer.parseInt(id);
        }

        Template template = this.templateService.findTemplateById(_id);
        model.addAttribute("template", template);

        List<ProcessDefinition> processDefinitionList = processDefinitionService.findAllProcessDefinitions();
        model.addAttribute("processDefinitionList", processDefinitionList);

        return "template/editUI";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(MultipartFile resource, HttpServletRequest request, Template template) {
        try {
            Template oldTemplate = this.templateService.findTemplateById(template.getId());
            if (StringUtils.isBlank(resource.getOriginalFilename())) {
                template.setPath(oldTemplate.getPath());
            } else {
                File oldFile = new File(oldTemplate.getPath());
                if (oldFile.exists()) {
                    oldFile.delete();
                }
                File file = FileUploadUtils.uploadFile(resource, request);
                template.setPath(file.getAbsolutePath());
            }
            this.templateService.modifyTemplate(template);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/template/list";
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public String download(HttpServletResponse response, @PathVariable String id) {
        ServletOutputStream outputStream = null;
        FileInputStream inputStream = null;
        int i = 0;
        byte[] buffer = null;
        Integer _id = null;

        try {
            if (StringUtils.isNotBlank(id)) {
                _id = Integer.parseInt(id);
            }

            Template template = this.templateService.findTemplateById(_id);
            File file = new File(template.getPath());
            String fileName = new String((template.getName()+".doc").getBytes("gbk"),"iso-8859-1");
            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition", "attachment;filename=" + fileName);

            outputStream = response.getOutputStream();
            inputStream = new FileInputStream(file);
            buffer = new byte[inputStream.available()];

            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.flush();
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
