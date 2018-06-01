package com.my.oa.system.web.spring.controller;


import com.my.oa.system.domain.Privilege;
import com.my.oa.system.service.PrivilegeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Controller
@RequestMapping("/index")
public class IndexController  {
    @Resource(name = "privilegeService")
    private PrivilegeService privilegeService;

    @RequestMapping("/index")
    public String index() {
        return "../../index";
    }

    @RequestMapping("/top")
    public String top() {
        return "../../top";
    }

    @RequestMapping("/left")
    public String left(Model model) {
        List<Privilege> topPrivilegesList = this.privilegeService.findAllTopPrivilege();
        //这里只要二级菜单就可以
        /*for (PrivilegeInterceptor privilege : topPrivilegesList) {

            for(Iterator<PrivilegeInterceptor> it = privilege.getChildren().iterator(); it.hasNext(); ) {
                PrivilegeInterceptor p =  it.next();
                //查找子权限的子权限
                PrivilegeInterceptor privilege1 = this.privilegeService.findPrivilegeById(p.getId());
                p.setChildren(privilege1.getChildren());
            }

        }*/
        model.addAttribute("topPrivilegesList",topPrivilegesList);

        return "../../left";
    }

    @RequestMapping("/right")
    public String right() {
        return "../../right";
    }

    @RequestMapping("/bottom")
    public String bottom() {
        return "../../bottom";
    }
}
