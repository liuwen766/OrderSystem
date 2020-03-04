package com.liuwen.controller;


import com.liuwen.entity.User;
import com.liuwen.entity.UserVO;
import com.liuwen.feign.UserFeign;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Resource
    private UserFeign userFeign;

//    @GetMapping("/findAll/{page}/{limit}")        //用于测试
//    @ResponseBody
//    public List<User> findAll(@PathVariable("page") int page, @PathVariable("limit") int limit) {
//        int index = (page-1)*limit;
//        return userFeign.findAll(index,limit);
//    }

    //为了适应前端Layui框架，需要返回符合传递参数格式的数据。  封装成UserVO
    @GetMapping("/findAll")
    @ResponseBody
    public UserVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        int index = (page-1)*limit;
        UserVO userVO = new UserVO();
        userVO.setCode(0);userVO.setMsg("");userVO.setCount(userFeign.count());userVO.setData(userFeign.findAll(index,limit));
        return userVO;
    }

//    @GetMapping("/findById/{id}")
//    public User findById(@PathVariable("id") long id) {
//        return userFeign.findById(id);
//    }

    @GetMapping("/count")
    public int count() {
        return userFeign.count();
    }

    @PostMapping("/save")
// @RequestBody  User user   如果是json数据就要加该注解，将json数据转换为User对象
// 前台表单数据的提交不需要加注解，因为不是json。但是服务间的数据传送是json格式的，所以在user服务模块，该处需要加注解 @RequestBody
    public String save(User user) {
        user.setRegisterdate(new Date());
        userFeign.save(user);
        return "redirect:/menu/redirect/user_manage";
    }

//    @PutMapping("/update")
//    public void update(@RequestBody User user) {
//        userFeign.update(user);
//    }

    @GetMapping("/deleteById/{id}")   //是@GetMapping  不是@DeleteMapping，由前端window.location.href="/menu/findById/"+data.id传过来的请求是一个Get请求
    public String  deleteById(@PathVariable("id") long id) {
        userFeign.deleteById(id);
        return "redirect:/menu/redirect/user_manage";
    }

}
