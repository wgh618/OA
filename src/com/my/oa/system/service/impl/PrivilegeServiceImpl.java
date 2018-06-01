package com.my.oa.system.service.impl;

import com.my.oa.system.domain.Privilege;
import com.my.oa.system.domain.RolePrivilege;
import com.my.oa.system.domain.User;
import com.my.oa.system.domain.UserRole;
import com.my.oa.system.mapper.PrivilegeMapper;
import com.my.oa.system.service.PrivilegeService;
import com.my.oa.system.service.RolePrivilegeService;
import com.my.oa.system.service.UserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {

    @Resource(name = "privilegeMapper")
    private PrivilegeMapper privilegeMapper;

    @Resource(name = "userRoleService")
    private UserRoleService userRoleService;

    @Resource(name = "rolePrivilegeService")
    private RolePrivilegeService rolePrivilegeService;

    @Override
    public List<Privilege> findAllPrivilege() {
        return this.privilegeMapper.queryAll();
    }

    @Override
    public List<Privilege> findAllTopPrivilege() {
        return this.privilegeMapper.queryAllTop();
    }

    @Override
    public Privilege findPrivilegeById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("");
        }
        return this.privilegeMapper.load(id);
    }

    @Override
    public List<Privilege> findPrivilegeByUser(User loginUser) {
        Set<Privilege> privilegeSet = new HashSet<Privilege>();
        List<Privilege> result = new ArrayList<Privilege>();
        List<UserRole> userRoleList = this.userRoleService.findUserRoleByUserId(loginUser.getId());

        if(userRoleList != null && userRoleList.size() > 0) {
            for (UserRole userRole : userRoleList) {
                List<RolePrivilege> rolePrivilegeList = rolePrivilegeService.findRolePrivilegeByRoleId(userRole.getRole
                        ().getId());
                for (RolePrivilege rolePrivilege : rolePrivilegeList) {
                    privilegeSet.add(rolePrivilege.getPrivilege());
                }
            }
        }
        CollectionUtils.addAll(result,privilegeSet.iterator());
        return result;
    }
}
