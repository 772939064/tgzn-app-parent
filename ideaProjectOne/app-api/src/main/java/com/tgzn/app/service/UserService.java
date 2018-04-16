package com.tgzn.app.service;


import com.tgzn.app.common.ServerResponse;

/**
 * 通用接口
 */
public interface UserService {
    ServerResponse login(String username, String password);

    ServerResponse getUserAllInfo();

    ServerResponse queryUserInfoById(Integer userId);
}
