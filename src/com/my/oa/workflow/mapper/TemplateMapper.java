package com.my.oa.workflow.mapper;


import com.my.oa.workflow.domain.Template;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface TemplateMapper {

    public List<Template> findAll();

    public void add(Template template);

    public Template load(Integer id);

    public void delete(Template template);

    public void update(Template template);
}
