package com.my.oa.workflow.mapper;


import com.my.oa.workflow.domain.Application;
import com.my.oa.workflow.domain.Approve;
import com.my.oa.workflow.domain.Template;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface ApplicationMapper {

    public void add(Application application);

    public Application load(Integer id);

    public List<Application> findApplicationsByUserId(Integer id);

    public List<Approve> findApprovesByApplicationId(Integer id);

    public void update(Application application);
}
