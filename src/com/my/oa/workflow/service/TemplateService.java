package com.my.oa.workflow.service;

import com.my.oa.workflow.domain.Template;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface TemplateService {
    public List<Template> findAllTemplates();

    public void addTemplate(Template template);

    public Template findTemplateById(Integer id);

    public void deleteTemplate(Template template);

    public void modifyTemplate(Template template);
}
