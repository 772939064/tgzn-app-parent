package com.tgzn.app.appconsumertest.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tgzn.app.common.ServerResponse;
import com.tgzn.app.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TestController
 *
 * @author Yarn
 * @date 2018-04-09 16:18
 **/
@RestController
public class TestController {

    @Reference
    private UserService userService;

    @GetMapping("test")
    public ServerResponse test(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    @GetMapping("test1")
    public ServerResponse test1(@RequestParam Integer userId) {
        ServerResponse currentUser = userService.queryUserInfoById(userId);
        if (currentUser.isSuccess()) {
            Object data = currentUser.getData();
            System.out.println(data.getClass());
            if(data instanceof HashMap) {
                Map<String, Object> resultMap = (Map<String, Object>) data;
                resultMap.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
            }
        }
        return currentUser;
    }


    @GetMapping("test2")
    public ServerResponse test2() {
        ServerResponse currentUser = userService.getUserAllInfo();
        if (currentUser.isSuccess()) {
            Object data = currentUser.getData();
            System.out.println(data.getClass());
            if (data instanceof List) {
                List<Object> list = (List<Object>) data;
                list.forEach( e -> System.out.println(e));
            }
        }
        return currentUser;
    }
}
