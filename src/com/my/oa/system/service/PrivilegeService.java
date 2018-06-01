package com.my.oa.system.service;

import com.my.oa.system.domain.Privilege;
import com.my.oa.system.domain.User;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface PrivilegeService {
    public List<Privilege> findAllPrivilege();

    public List<Privilege> findAllTopPrivilege();

    public Privilege findPrivilegeById(Integer id);

    public List<Privilege> findPrivilegeByUser(User loginUser);
}
