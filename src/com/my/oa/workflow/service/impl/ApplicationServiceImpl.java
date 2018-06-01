package com.my.oa.workflow.service.impl;

import com.my.oa.system.domain.User;
import com.my.oa.workflow.domain.Application;
import com.my.oa.workflow.domain.Approve;
import com.my.oa.workflow.mapper.ApplicationMapper;
import com.my.oa.workflow.service.ApplicationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    public void addApplication(Application application) {
        if (application == null) {
            throw new IllegalArgumentException("");
        }

        this.applicationMapper.add(application);
    }

    @Override
    public List<Application> findAllApplicationsByUser(User user) {
        return applicationMapper.findApplicationsByUserId(user.getId());
    }

    @Override
    public Application findApplicationById(Integer id) {
        Application application = null;

        if(id == null || id <= 0) {
            throw new IllegalArgumentException("");
        }

        try {
            application = this.applicationMapper.load(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return application;
    }

    @Override
    public List<Approve> findAllApprovesByApplicationId(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("");
        }

        return applicationMapper.findApprovesByApplicationId(id);
    }
/*
    @Override
    public List<ApplicationVO> findApplicationsByStatusWithSelf(User user, String status) {
        List<ApplicationVO> applicationVOList = new ArrayList<ApplicationVO>();
        ApplicationVO applicationVO = null;
        if ("".equals(status)) {
//            List<Application> applicationList = this.findAllApplicationsWithSelf(user);
//            for (Application application : applicationList) {
//                applicationVO = new ApplicationVO();
//                applicationVO.setApplicantName(application.getApplicant().getName());
//                applicationVOList.add(applicationVO);
//            }
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", user.getId());
            map.put("status", status);
            applicationVOList = this.applicationMapper.findApplicationVOsByUserId(map);
        }
        return applicationVOList;
    }*/

    @Override
    public void modifyApplicationStatus(Application application, String status) {
        if(application == null) {
            throw new IllegalArgumentException("");
        }

        if(StringUtils.isBlank(status)) {
            throw new IllegalArgumentException("");
        }

        application.setStatus(status);

        this.applicationMapper.update(application);
    }
}

