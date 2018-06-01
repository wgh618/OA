package com.my.oa.system.service.impl;

import com.my.oa.system.domain.Role;
import com.my.oa.system.domain.RolePrivilege;
import com.my.oa.system.mapper.PrivilegeMapper;
import com.my.oa.system.mapper.RoleMapper;
import com.my.oa.system.mapper.RolePrivilegeMapper;
import com.my.oa.system.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource(name = "roleMapper")
    private RoleMapper roleMapper;

    @Resource(name = "rolePrivilegeMapper")
    private RolePrivilegeMapper rolePrivilegeMapper;

    @Resource(name = "privilegeMapper")
    private PrivilegeMapper privilegeMapper;

    @Override
    public void addRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException();
        }
        this.roleMapper.save(role);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Role> findAllRoles() {
        return this.roleMapper.queryAll();
    }

    @Override
    public Role findRoleById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("");
        }
        return this.roleMapper.load(id);
    }

    @Override
    public void deleteRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("");
        }
        this.roleMapper.delete(role);
    }

    @Override
    public void modifyRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("");
        }
        this.roleMapper.update(role);
    }

    @Override
    public boolean checkUniqueByName(String name) {
        List roleList = this.findRoleByName(name);

        if (roleList == null || roleList.size() <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Role> findRoleByName(String name) {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("");
        }
        List roleList = this.roleMapper.findRoleByName(name);
        return roleList;
    }

    @Override
    public void setPrivilege(Role role, List<Integer> privilegeIdList) {

        if(role == null) {
            throw new IllegalArgumentException("");
        }

        if(privilegeIdList == null || privilegeIdList.size() <= 0) {
            throw new IllegalArgumentException("");
        }


        List<RolePrivilege> rolePrivilegeList = rolePrivilegeMapper.findByRoleId(role.getId());
        for (RolePrivilege rolePrivilege : rolePrivilegeList) {
            rolePrivilegeMapper.delete(rolePrivilege);
        }

        RolePrivilege rolePrivilege = null;
        for (Integer privilegeId : privilegeIdList) {
            rolePrivilege = new RolePrivilege();
            rolePrivilege.setRole(role);
            rolePrivilege.setPrivilege(privilegeMapper.load(privilegeId));

            rolePrivilegeMapper.save(rolePrivilege);
        }

    }
}
