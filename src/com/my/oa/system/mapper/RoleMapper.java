package com.my.oa.system.mapper;


import com.my.oa.system.domain.Role;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 */
public interface RoleMapper {
    public void save(Role user);

    public List<Role> queryAll();

    public Role load(Integer id);

    public void delete(Role user);

    public void update(Role user);

    public List<Role> findRoleByName(String name);
}
