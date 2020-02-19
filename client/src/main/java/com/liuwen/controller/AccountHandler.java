package com.liuwen.controller;

import com.liuwen.entity.Admin;
import com.liuwen.entity.User;
import com.liuwen.feign.AccountFeign;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccountHandler {

    @Resource
    private AccountFeign accountFeign;

    // 客户端登录
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("type") String type, HttpSession session)  {
        Object object = accountFeign.login(username, password, type);
        LinkedHashMap<String,Object> hashMap = (LinkedHashMap) object;
        String result = null;
        String idStr = null;
        long id = 0L;
        if(object == null){   /*登录失败*/
            result = "login";
        }else{
            switch (type){
                //  用户登录成功则订购点菜
                case "user":
                    User user = new User();
                    idStr = hashMap.get("id")+"";
                    id = Long.parseLong(idStr);
                    String nickname = (String)hashMap.get("nickname");
                    user.setId(id);
                    user.setNickname(nickname);
                    session.setAttribute("user",user);
                    result = "index";
                    break;
                // 管理员登录成功编辑菜单
                case "admin":
                    Admin admin = new Admin();
                    idStr = hashMap.get("id")+"";
                    id = Long.parseLong(idStr);
                    String username2 = (String)hashMap.get("username");
                    admin.setId(id);
                    admin.setUsername(username2);
                    session.setAttribute("admin",admin);
                    result = "main";       /*编辑菜单页面*/
                    break;
            }
        }

        System.out.println(object);
        return result;
    }

//    退出登录 (在客户端销毁session即可)
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }



}
