package com.my.oa.workflow.web.spring.controller;


import com.my.oa.system.domain.User;
import com.my.oa.workflow.domain.Application;
import com.my.oa.workflow.domain.Approve;
import com.my.oa.workflow.service.ApplicationService;
import com.my.oa.workflow.service.ApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Controller
@RequestMapping("/approve")
public class ApproveController {

    @Autowired
    private ApproveService approveService;

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping("/myTaskList")
    public String myTaskList(HttpSession session, Model model) {
        User approver = (User) session.getAttribute("loginUser");
        List<Application> applicationList = this.approveService.findApplicationsByApproving(approver);
        model.addAttribute("applicationList", applicationList);
        return "flow/my_task_list";
    }

    @RequestMapping(value = "/approve/{id}", method = RequestMethod.GET)
    public String approve(@PathVariable Integer id, Model model) {
//        Application application = this.applicationService.findApplicationById(id);
        model.addAttribute("applicationId", id);
        return "flow/approveUI";
    }

    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public String approve(HttpSession session, @RequestParam String approval, @RequestParam String suggestion, @RequestParam Integer applicationId) {
        User approver = (User) session.getAttribute("loginUser");

        Approve approve = new Approve();

        if ("true".equals(approval)) {
            approve.setIsPassed("同意");
        } else {
            approve.setIsPassed("不同意");
        }

        approve.setSuggestion(suggestion);
        approve.setApplication(this.applicationService.findApplicationById(applicationId));
        approve.setApproveDate(new Date());
        approve.setApprover(approver);

        this.approveService.approve(approve);

        return "redirect:/approve/myTaskList";
    }
}
