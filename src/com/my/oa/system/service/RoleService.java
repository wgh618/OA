package com.my.oa.system.service;

import com.my.oa.system.domain.Role;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface RoleService {

    public void addRole(Role role);

    public List<Role> findAllRoles();

    public Role findRoleById(Integer id);

    public void deleteRole(Role role);

    public void modifyRole(Role role);

    public boolean checkUniqueByName(String name);

    public List<Role> findRoleByName(String name);

    public void setPrivilege(Role role, List<Integer> privilegeIdList);
}
