package com.my.oa.workflow.service;


import com.my.oa.system.domain.User;
import com.my.oa.workflow.domain.Application;
import com.my.oa.workflow.domain.Approve;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface ApplicationService {

    public void addApplication(Application application);

    public List<Application> findAllApplicationsByUser(User user);
/*
    public List<ApplicationVO> findApplicationsByStatusWithSelf(User user, String status);
*/

    public List<Approve> findAllApprovesByApplicationId(Integer id);

    public Application findApplicationById(Integer id);

    public void modifyApplicationStatus(Application application, String status);
}
