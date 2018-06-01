package com.my.oa.workflow.service.impl;

import com.my.oa.workflow.domain.Template;
import com.my.oa.workflow.mapper.TemplateMapper;
import com.my.oa.workflow.service.TemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */

@Service("templateService")
@Transactional
public class TemplateServiceImpl implements TemplateService {

    @Resource(name = "templateMapper")
    private TemplateMapper templateMapper;

    @Override
    public List<Template> findAllTemplates() {
        return templateMapper.findAll();
    }

    @Override
    public void addTemplate(Template template) {
        if (template == null) {
            throw new IllegalArgumentException("");
        }

        this.templateMapper.add(template);
    }

    @Override
    public Template findTemplateById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("");
        }
        return this.templateMapper.load(id);
    }

    @Override
    public void deleteTemplate(Template template) {
        if (template == null) {
            throw new IllegalArgumentException("");
        }

        this.templateMapper.delete(template);
    }

    @Override
    public void modifyTemplate(Template template) {
        if (template == null) {
            throw new IllegalArgumentException("");
        }

        this.templateMapper.update(template);
    }
}
