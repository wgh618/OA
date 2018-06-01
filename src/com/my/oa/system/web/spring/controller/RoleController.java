package com.my.oa.system.web.spring.controller;

import com.my.oa.system.domain.Privilege;
import com.my.oa.system.domain.Role;
import com.my.oa.system.domain.RolePrivilege;
import com.my.oa.system.service.DepartmentService;
import com.my.oa.system.service.PrivilegeService;
import com.my.oa.system.service.RolePrivilegeService;
import com.my.oa.system.service.RoleService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016-11-01 20:52.
 *
 * @author 吴光辉
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "departmentService")
    private DepartmentService departmentService;

    @Resource(name = "privilegeService")
    private PrivilegeService privilegeService;

    @Resource(name = "rolePrivilegeService")
    private RolePrivilegeService rolePrivilegeService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<Role> roleList = this.roleService.findAllRoles();
        model.addAttribute("roleList", roleList);
        return "role/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        Role role = this.roleService.findRoleById(id);
        this.roleService.deleteRole(role);
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/editUI/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable Integer id, Model model) {
        Role role = this.roleService.findRoleById(id);
        model.addAttribute("role", role);
        return "role/editUI";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String modify(Role role, Model model) {
        Role role1 = this.roleService.findRoleById(role.getId());
        if (role1.getName().equals(role.getName())) {
            this.roleService.modifyRole(role);
            return "redirect:/role/list";
        }
        //校验唯一性
        boolean flag = this.roleService.checkUniqueByName(role.getName());
        if (flag) {
            this.roleService.modifyRole(role);
            return "redirect:/role/list";
        } else {
            model.addAttribute("errorMsg", "该岗位已经存在！请重新输入！");
            return "common/error";
        }


    }

    @RequestMapping(value = "/addUI", method = RequestMethod.GET)
    public String addUI() {
        return "role/saveUI";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Role role, Model model) {

        //校验唯一性
        boolean flag = this.roleService.checkUniqueByName(role.getName());
        if (flag) {
            this.roleService.addRole(role);
            return "redirect:/role/list";
        } else {
            model.addAttribute("errorMsg", "该岗位已经存在！请重新输入！");
            return "common/error";
        }
    }

    @RequestMapping(value = "/setPrivilegeUI/{id}", method = RequestMethod.GET)
    public String setPrivilege(@PathVariable Integer id, Model model) {

        Role role = this.roleService.findRoleById(id);
        model.addAttribute("role",role);

        List<Privilege> privilegeList = this.privilegeService.findAllTopPrivilege();
        for (Privilege privilege : privilegeList) {

            for( Iterator<Privilege> it = privilege.getChildren().iterator();  it.hasNext(); ) {
                Privilege p =  it.next();
                //查找子权限的子权限
                Privilege privilege1 = this.privilegeService.findPrivilegeById(p.getId());
                p.setChildren(privilege1.getChildren());
            }

        }
        model.addAttribute("privilegeList",privilegeList);

        List<RolePrivilege> rolePrivilegeList = this.rolePrivilegeService.findRolePrivilegeByRoleId(role.getId());
        model.addAttribute("rolePrivilegeList",rolePrivilegeList);

        return "role/setPrivilegeUI";
    }

    @RequestMapping(value = "/setPrivilege", method = RequestMethod.POST)
    public String setPrivilege(@RequestParam Integer roleId, @RequestParam List<Integer> resourceIdList) {

        Role role = this.roleService.findRoleById(roleId);

        this.roleService.setPrivilege(role,resourceIdList);
        return "redirect:/role/list";
    }
}
