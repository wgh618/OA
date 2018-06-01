package com.my.oa.system.mapper;


import com.my.oa.system.domain.UserRole;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 */
public interface UserRoleMapper {
    public void save(UserRole userRole);

    public List<UserRole> findByUserId(Integer id);

    public void delete(UserRole userRole);

    public void update(UserRole userRole);

    public List<UserRole> findByRoleId(Integer id);
}
