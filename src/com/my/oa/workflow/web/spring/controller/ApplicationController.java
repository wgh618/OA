package com.my.oa.workflow.web.spring.controller;

import com.my.oa.core.util.FileUploadUtils;
import com.my.oa.system.domain.User;
import com.my.oa.system.domain.UserRole;
import com.my.oa.system.service.UserRoleService;
import com.my.oa.workflow.domain.Application;
import com.my.oa.workflow.domain.Approve;
import com.my.oa.workflow.domain.Template;
import com.my.oa.workflow.service.ApplicationService;
import com.my.oa.workflow.service.TemplateService;
import com.my.oa.workflow.service.WorkFlowService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Controller
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private WorkFlowService workFlowService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 模板列表
     * @param model
     * @return
     */
    @RequestMapping("/templateList")
    public String templateList(Model model) {
        List<Template> templateList = templateService.findAllTemplates();
        model.addAttribute("templateList", templateList);
        return "flow/template_list";
    }

    /**
     * 提交申请跳转
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    public String submit(@PathVariable Integer id, Model model) {
        List<UserRole> userRoleList = userRoleService.findUserRoleByRoleId(id);

        model.addAttribute("userRoleList", userRoleList);
        model.addAttribute("templateId", id);

        return "flow/submitUI";
    }

    /**
     * 提交申请
     * @param resource
     * @param request
     * @param templateId
     * @param manager
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(MultipartFile resource, HttpServletRequest request, Integer templateId, String manager) {
        try {
            File file = FileUploadUtils.uploadFile(resource, request);
            String docPath = file.getAbsolutePath();

            Application application = new Application();
            application.setPath(docPath);

            User user = (User) request.getSession(true).getAttribute("loginUser");
            application.setApplicant(user);
            application.setApplyTime(new Date());
            application.setStatus(Application.STATUS_RUNNING);


            Template template = templateService.findTemplateById(templateId);
            String title = template.getName() + "_" + user.getLoginName() + "_" + new SimpleDateFormat("yyyy-MM-dd")
                    .format(new Date());

            application.setTitle(title);
            application.setTemplate(template);

            applicationService.addApplication(application);

            // 启动工作流
            this.workFlowService.startWorkFlow(application, manager);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/application/list";
    }

    /**
     * 我的申请列表
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String applicationList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        List<Application> applicationList = this.applicationService.findAllApplicationsByUser(user);
        model.addAttribute("applicationList", applicationList);
        return "flow/submitted_list";
    }

    /*@RequestMapping("/search")
    public String search(HttpSession session, Model model, String status) {
        User user = (User) session.getAttribute("user");
        List<ApplicationVO> applicationVOList = this.applicationService.findApplicationsByStatusWithSelf(user, status);
        model.addAttribute("applicationVOList", applicationVOList);
        model.addAttribute("status", status);
        return "flow/submitted_list_1";
    }*/

    @RequestMapping("/showForm/{id}")
    public String showForm(@PathVariable Integer id,Model model) {
        model.addAttribute("applicationId",id);
        return "flow/show_form";
    }

    /**
     * 下载申请信息
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/download/{id}",method = RequestMethod.GET)
    public String download(@PathVariable("id") Integer id , HttpServletResponse response) {
        Application application = this.applicationService.findApplicationById(id);
        ServletOutputStream outputStream = null;
        FileInputStream inputStream = null;
        int i = 0;
        byte[] buffer = null;
        Integer _id = null;

        try {
            File file = new File(application.getPath());

            String fileName = new String(("申请信息.doc").getBytes("gbk"),"iso-8859-1");

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

    /**
     * 查看流转记录
     * @param session
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/approvedHistory/{id}")
    public String approvedHistory(HttpSession session, @PathVariable Integer id, Model model) {
//        User user = (User) session.getAttribute("user");
        List<Approve> approveList = this.applicationService.findAllApprovesByApplicationId(id);
        model.addAttribute("approveList", approveList);
        return "flow/approved_history";
    }
}

