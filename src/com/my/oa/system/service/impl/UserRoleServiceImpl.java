package com.my.oa.system.service.impl;

import com.my.oa.system.domain.User;
import com.my.oa.system.domain.UserRole;
import com.my.oa.system.mapper.UserRoleMapper;
import com.my.oa.system.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Resource(name = "userRoleMapper")
    private UserRoleMapper userRoleMapper;

    @Override
    public void addUserRole(UserRole userRole) {
        if (userRole == null) {
            throw new IllegalArgumentException();
        }
        this.userRoleMapper.save(userRole);
    }


    @Override
    public void deleteUserRole(UserRole userRole) {
        if (userRole == null) {
            throw new IllegalArgumentException("");
        }
        this.userRoleMapper.delete(userRole);
    }

    @Override
    public void modifyUserRole(UserRole userRole) {
        if (userRole == null) {
            throw new IllegalArgumentException("");
        }
        this.userRoleMapper.update(userRole);
    }

    @Override
    public List<UserRole> findUserRoleByUserId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("");
        }
        return this.userRoleMapper.findByUserId(id);
    }

    @Override
    public List<UserRole> findUserRoleByRoleId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("");
        }
        return this.userRoleMapper.findByRoleId(id);
    }

    @Override
    public List<String> findRoleNamesByUser(User user) {
        List<String> nameList = new ArrayList<String>();

        List<UserRole> userRoleList = this.userRoleMapper.findByUserId(user.getId());
        if (userRoleList != null && userRoleList.size() > 0) {
            for (UserRole userRole : userRoleList) {
                nameList.add(userRole.getRole().getName());
            }
        }
        return nameList;
    }
}
