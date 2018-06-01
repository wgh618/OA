package com.my.oa.system.service;

import com.my.oa.system.domain.User;
import com.my.oa.system.domain.UserRole;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface UserRoleService {
    public void addUserRole(UserRole userRole);

    public List<UserRole> findUserRoleByUserId(Integer id);

    public List<UserRole> findUserRoleByRoleId(Integer id);

    public void deleteUserRole(UserRole userRole);

    public void modifyUserRole(UserRole userRole);

    public List<String> findRoleNamesByUser(User user);
}
