package com.my.oa.system.service.impl;

import com.my.oa.system.domain.RolePrivilege;
import com.my.oa.system.mapper.RolePrivilegeMapper;
import com.my.oa.system.service.RolePrivilegeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Service("rolePrivilegeService")
public class RolePrivilegeServiceImpl implements RolePrivilegeService {

    @Resource(name = "rolePrivilegeMapper")
    private RolePrivilegeMapper rolePrivilegeMapper;

    @Override
    public void addRolePrivilege(RolePrivilege rolePrivilege) {
        if (rolePrivilege == null) {
            throw new IllegalArgumentException("");
        }
        this.rolePrivilegeMapper.save(rolePrivilege);
    }

    @Override
    public List<RolePrivilege> findRolePrivilegeByRoleId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("");
        }
        return  this.rolePrivilegeMapper.findByRoleId(id);
    }

    @Override
    public void deleteRolePrivilege(RolePrivilege rolePrivilege) {
        if (rolePrivilege == null) {
            throw new IllegalArgumentException("");
        }
        this.rolePrivilegeMapper.delete(rolePrivilege);
    }
}
