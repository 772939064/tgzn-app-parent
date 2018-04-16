package com.tgzn.app.appuser.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tgzn.app.appuser.mapper.UserMapper;
import com.tgzn.app.appuser.pojo.User;
import com.tgzn.app.common.ServerResponse;
import com.tgzn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * UserServiceImpl
 *
 * @author Yarn
 * @date 2018-04-09 9:49
 **/
@Component
@Service(version = "1.0.0",group = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse login(String username, String password) {
        User currentUser = userMapper.selectOne(new User(username, password));
        if (currentUser != null) {
            return ServerResponse.createBySuccess(currentUser);
        }
        return ServerResponse.createByErrorMessage("用户名或者密码错误");
    }


    public ServerResponse modifyPassword(Integer userId, String oldPassword, String newPassword) {
        User currentUser = userMapper.selectOne(new User(userId, oldPassword));
        if (currentUser != null) {
            Integer resultRow = userMapper.updateById(new User(userId, newPassword));
            if (resultRow > 0) {
                return ServerResponse.createBySuccessMessage("密码修改成功");
            }
        } else {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        return ServerResponse.createByErrorMessage("密码修改失败, 请重试");
    }


    public ServerResponse register(String username, String password) {
            Integer rowCount = userMapper.selectCount(new EntityWrapper<User>().eq("username", username));
            if (rowCount > 0) {
                return ServerResponse.createByErrorMessage("用户名已被注册");
            }

            Integer resultRow = userMapper.insert(new User(username, password));
            if (resultRow > 0) {
                return ServerResponse.createBySuccessMessage("注册成功");
            }
            return ServerResponse.createByErrorMessage("用户注册失败！！");
    }

    @Override
    public ServerResponse getUserAllInfo() {
        List<User> userList = userMapper.selectList(new EntityWrapper<>(null));
        return ServerResponse.createBySuccess(userList);
    }

    @Override
    public ServerResponse queryUserInfoById(Integer userId) {
        return null;
    }
}
