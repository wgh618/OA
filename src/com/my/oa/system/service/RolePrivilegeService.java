package com.my.oa.system.service;

import com.my.oa.system.domain.RolePrivilege;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface RolePrivilegeService {
    public void addRolePrivilege(RolePrivilege rolePrivilege);

    public List<RolePrivilege> findRolePrivilegeByRoleId(Integer id);

    public void deleteRolePrivilege(RolePrivilege rolePrivilege);

}
