package com.my.oa.core.web.spring.controller;

import com.my.oa.core.util.MD5Util;
import com.my.oa.system.domain.User;
import com.my.oa.system.domain.UserRole;
import com.my.oa.system.service.UserRoleService;
import com.my.oa.system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Controller
@RequestMapping("/userSetting")
public class UserSetting {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "userRoleService")
    private UserRoleService userRoleService;

    @RequestMapping(value = "/editPasswordUI", method = RequestMethod.GET)
    public String editPassword() {

        return "userConfig/editPasswordUI";
    }

    @RequestMapping(value = "/editPassword", method = RequestMethod.POST)
    public String editPassword(@RequestParam("oldPassword") String password, @RequestParam("newPassword1") String
            newPassword, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user.getPassword().equals(MD5Util.getMD5(password))) {
            user.setPassword(MD5Util.getMD5(newPassword));
            user.setLastModified(new Date());
            this.userService.modifyUser(user);
            model.addAttribute("errorMsg", "修改密码成功");
        } else {
            model.addAttribute("errorMsg", "修改失败，原密码不正确");
        }

        return "common/error";
    }

    @RequestMapping(value = "/editUserInfoUI", method = RequestMethod.GET)
    public String editUserInfo(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        //查询该用户的所有岗位
        List<UserRole> userRoleList = this.userRoleService.findUserRoleByUserId(user.getId());
        model.addAttribute("userRoles", userRoleList);
        return "userConfig/editUserInfoUI";
    }

    @RequestMapping(value = "/editUserInfo", method = RequestMethod.POST)
    public String editUserInfo(@RequestParam("telephone") String telephone, @RequestParam("email") String
            email, @RequestParam("description") String description, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        try {
            user.setTelephone(telephone);
            user.setEmail(email);
            user.setDescription(description);
            user.setLastModified(new Date());
            this.userService.modifyUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", "修改失败！");
        }
        model.addAttribute("errorMsg", "修改成功！");
        return "common/error";
    }
}
