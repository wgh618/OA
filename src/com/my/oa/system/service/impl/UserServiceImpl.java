package com.my.oa.system.service.impl;

import com.my.oa.core.util.MD5Util;
import com.my.oa.system.domain.User;
import com.my.oa.system.mapper.UserMapper;
import com.my.oa.system.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        this.userMapper.save(user);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<User> findAllUsers() {
        return this.userMapper.queryAll();
    }

    @Override
    public User findUserById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("");
        }
        return this.userMapper.load(id);
    }

    @Override
    public void deleteUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("");
        }
        this.userMapper.delete(user);
    }

    @Override
    public void modifyUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("");
        }
        this.userMapper.update(user);
    }

    @Override
    public List<User> findUserByName(String loginName) {
        if (loginName == null || "".equals(loginName)) {
            throw new IllegalArgumentException("");
        }
        List userList = this.userMapper.findUserByName(loginName);
        return userList;
    }

    @Override
    public boolean checkUniqueByName(String name) {
        List userList = this.findUserByName(name);

        if (userList == null || userList.size() <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public User checkLogin(User loginUser) {
        if (loginUser == null ) {
            throw new IllegalArgumentException("");
        }
        loginUser.setPassword(MD5Util.getMD5(loginUser.getPassword()));
        User user = this.userMapper.findUserByCondition(loginUser);
        return user;
    }
}
