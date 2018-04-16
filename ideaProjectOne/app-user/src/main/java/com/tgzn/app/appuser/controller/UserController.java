package com.tgzn.app.appuser.controller;


import com.tgzn.app.common.ServerResponse;
import com.tgzn.app.common.SysConst;
import com.tgzn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * UserController
 *
 * @author Yarn
 * @date 2018-04-09 9:47
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ServerResponse login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        ServerResponse currentUser = userService.login(username, password);
        if (currentUser.isSuccess()) {
            session.setAttribute(SysConst.User.CURRENT_USER, currentUser.getData());
        }
        return currentUser;
    }

    @PostMapping("modifyPassword")
    public ServerResponse modifyPassword(Integer userId, String oldPassword, String newPassword) {
        return userService.modifyPassword(userId, oldPassword, newPassword);
    }

    @PostMapping("register")
    public ServerResponse register(String username, String password){
        return userService.register(username, password);
    }

    @GetMapping("getUserAllInfo")
    public ServerResponse getUserAllInfo(){
        return userService.getUserAllInfo();
    }
}
