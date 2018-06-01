package com.my.oa.system.service;

import com.my.oa.system.domain.User;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface UserService {

    public void addUser(User user);

    public List<User> findAllUsers();

    public User findUserById(Integer id);

    public void deleteUser(User user);

    public void modifyUser(User user);

    public boolean checkUniqueByName(String loginName);

    public List<User> findUserByName(String loginName);

    public User checkLogin(User loginuser);
}
