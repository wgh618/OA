package com.my.oa.system.mapper;


import com.my.oa.system.domain.RolePrivilege;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 */
public interface RolePrivilegeMapper {
    public void save(RolePrivilege rolePrivilege);

    public List<RolePrivilege> findByRoleId(Integer id);

    public void delete(RolePrivilege rolePrivilege);
}
