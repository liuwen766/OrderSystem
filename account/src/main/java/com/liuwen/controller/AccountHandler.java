package com.liuwen.controller;

import com.liuwen.repository.AdminRepository;
import com.liuwen.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class AccountHandler {
    // 多态的使用，面试点
    //一个Account 包括 user 和 admin
    @Resource
    private UserRepository userRepository;
    @Resource
    private AdminRepository adminRepository;
    //登录界面只有一个，所以需要整合  管理员Admin  和  普通用户User
    //使用多态，一个方法可以返回两个对象，Object尽可以表示user也可以表示admin
    @GetMapping("/login/{username}/{password}/{type}")
    public Object login(@PathVariable("username") String username,@PathVariable("password") String password, @PathVariable("type") String type){
        Object object = null;
        switch (type){        //type是由前台传过来的数据
            case "user":
                object = userRepository.login(username,password);
                break;
            case "admin":
                object = adminRepository.login(username,password);
                break;
        }
        return object;      //用户名和密码正确，则查询到用户并返回，否则登录失败
    }


}
