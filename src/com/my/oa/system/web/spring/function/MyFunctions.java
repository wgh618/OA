package com.my.oa.system.web.spring.function;


import com.my.oa.system.domain.Privilege;
import com.my.oa.system.domain.RolePrivilege;
import com.my.oa.system.domain.User;
import com.my.oa.system.service.UserRoleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18 16:24.
 *
 * @author 吴光辉
 */
@Component("myFunctions")
public class MyFunctions {

    private static UserRoleService userRoleService;

    @Resource(name = "userRoleService")
    public void setUserRoleService(UserRoleService userRoleService) {
        MyFunctions.userRoleService = userRoleService;
    }

    public static List<String> findRoleNamesByUser(User user) {
        return userRoleService.findRoleNamesByUser(user);
    }

    public static String isAssignedPrivilege(Privilege privilege, List<RolePrivilege> rolePrivilegeList) {
        if (privilege == null) {
            throw new IllegalArgumentException("");
        }

        if (rolePrivilegeList == null || rolePrivilegeList.size()<=0) {
            return "";
        }
        for (RolePrivilege rolePrivilege : rolePrivilegeList) {
            if (privilege.getId() == rolePrivilege.getPrivilege().getId()) {
                return "checked";
            }
        }
        return "";
    }
}
