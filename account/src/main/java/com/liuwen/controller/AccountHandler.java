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
    //登录界面只有一个，所以需要整合   管理员Admin  和  普通用户User
    @Resource
    private UserRepository userRepository;
    @Resource
    private AdminRepository adminRepository;

    //使用多态，一个方法可以返回两个对象
    @GetMapping("/login/{username}/{password}/{type}")
    public Object login(@PathVariable("username") String username,@PathVariable("password") String password, @PathVariable("type") String type){
        Object object = null;
        switch (type){
            case "user":
                object = userRepository.login(username,password);
                break;
            case "admin":
                object = adminRepository.login(username,password);
                break;
        }
        return object;
    }


}
