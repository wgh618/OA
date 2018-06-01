package com.my.oa.system.mapper;


import com.my.oa.system.domain.Privilege;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 */
public interface PrivilegeMapper {
    public void save(Privilege privilege);

    public List<Privilege> queryAll();

    public void delete(Privilege privilege);

    public List<Privilege> queryAllTop();

    public Privilege load(Integer id);
}
