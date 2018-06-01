package com.my.oa.system.mapper;


import com.my.oa.system.domain.User;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 */
public interface UserMapper {
    public void save(User user);

    public List<User> queryAll();

    public User load(Integer id);

    public void delete(User user);

    public void update(User user);

    public List<User> findUserByName(String loginName);

    public User findUserByCondition(User loginUser);
}
