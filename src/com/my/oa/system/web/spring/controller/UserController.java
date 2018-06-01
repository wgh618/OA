package com.my.oa.system.web.spring.controller;

import com.my.oa.core.util.MD5Util;
import com.my.oa.system.domain.*;
import com.my.oa.system.service.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016-11-01 20:52.
 *
 * @author 吴光辉
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "departmentService")
    private DepartmentService departmentService;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "userRoleService")
    private UserRoleService userRoleService;

    @Resource(name = "privilegeService")
    private PrivilegeService privilegeService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> userList = this.userService.findAllUsers();
        model.addAttribute("userList", userList);
        return "user/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        User user = this.userService.findUserById(id);
        this.userService.deleteUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/editUI/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable Integer id, Model model) {
        //查找所有部门
        List<Department> departmentList = this.departmentService.findAllDepartments();
        model.addAttribute("departmentList", departmentList);

        //查找所有岗位
        List<Role> roleList = this.roleService.findAllRoles();
        model.addAttribute("roleList", roleList);

        //查询该用户的所有岗位
        List<UserRole> userRoleList = this.userRoleService.findUserRoleByUserId(id);
        model.addAttribute("userRoles", userRoleList);

        User user = this.userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/editUI";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String modify(User user, @RequestParam("department_id") int departmentId, @RequestParam("role_ids") int[]
            roleIds, Model model) {
        User user1 = this.userService.findUserById(user.getId());
        if (!user1.getLoginName().equals(user.getLoginName())) {
            //校验唯一性
            boolean flag = this.userService.checkUniqueByName(user.getLoginName());
            if (!flag) {
                model.addAttribute("errorMsg", "该登录名已经存在！请重新输入！");
                return "common/error";
            }
        }

        if (departmentId != 0) {
            user.setDepartment(this.departmentService.findDepartmentById(departmentId));
        } else {
            user.setDepartment(null);
        }
        user.setCreatedTime(user1.getCreatedTime());
        user.setLastModified(new Date());

        this.userService.modifyUser(user);
        List<UserRole> userRoleList = this.userRoleService.findUserRoleByUserId(user.getId());
        for (int i = 0; i < userRoleList.size(); i++ ) {
            this.userRoleService.deleteUserRole(userRoleList.get(i));
        }
        for (int i = 0; i < roleIds.length; i++) {
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            Role role = this.roleService.findRoleById(roleIds[i]);
            userRole.setRole(role);
            this.userRoleService.addUserRole(userRole);
        }

        return "redirect:/user/list";
    }

    @RequestMapping(value = "/addUI", method = RequestMethod.GET)
    public String add(Model model) {
        //查找所有部门
        List<Department> departmentList = this.departmentService.findAllDepartments();
        model.addAttribute("departmentList", departmentList);

        //查找所有岗位
        List<Role> roleList = this.roleService.findAllRoles();
        model.addAttribute("roleList", roleList);
        return "user/saveUI";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(User user, @RequestParam("department_id") int departmentId, @RequestParam("role_ids") int[]
            roleIds, Model model) {
        //校验唯一性
        boolean flag = this.userService.checkUniqueByName(user.getLoginName());
        if (flag) {
            if (departmentId != 0) {
                user.setDepartment(this.departmentService.findDepartmentById(departmentId));
            } else {
                user.setDepartment(null);
            }
            user.setCreatedTime(new Date());
            user.setLastModified(new Date());

            user.setPassword(MD5Util.getMD5("1234"));
            this.userService.addUser(user);
            for (int i = 0; i < roleIds.length; i++) {
                UserRole userRole = new UserRole();
                userRole.setUser(user);
                Role role = this.roleService.findRoleById(roleIds[i]);
                userRole.setRole(role);
                this.userRoleService.addUserRole(userRole);
            }

            return "redirect:/user/list";
        } else {
            model.addAttribute("errorMsg", "该登录名已经存在！请重新输入！");
            return "common/error";
        }

    }

    @RequestMapping(value = "/initPassword/{id}", method = RequestMethod.GET)
    public String initPassword(@PathVariable Integer id) {
        User user = this.userService.findUserById(id);
        user.setPassword(MD5Util.getMD5("1234"));
        user.setLastModified(new Date());
        this.userService.modifyUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "user/loginUI";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpSession session) {
        User loginUser = this.userService.checkLogin(user);
        if (loginUser != null) {
            session.setAttribute("loginUser",loginUser);
            List<Privilege> privilegeList =  this.privilegeService.findPrivilegeByUser(loginUser);
            session.setAttribute("privilegeList",privilegeList);
            return "../../index";
        } else {
            return "common/login_error";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        session.removeAttribute("privilegeList");
        return "user/logout";
    }
}
