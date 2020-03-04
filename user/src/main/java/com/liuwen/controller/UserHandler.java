package com.liuwen.controller;


import com.liuwen.entity.User;
import com.liuwen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index(){
        return this.port;
    }

    @Resource
    public UserRepository userRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public List<User> findAll(@PathVariable("index") int index,@PathVariable("limit")int limit){
        return userRepository.findAll(index,limit);
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") long id){
        return userRepository.findById(id);
    }

    @GetMapping("/count")
    public int count(){
        return userRepository.count();
    }

    @PostMapping("/save")
    // 前端表单数据的提交不需要加注解，因为是json数据，不需要转换为Java对象。但是服务间的数据传送是json格式的，所以在user服务模块，该处需要加注解 @RequestBody
     /*后端@RequestBody注解对应的类在将HTTP的输入流(含请求体)装配到目标类(即:@RequestBody后面
    的类)时，会根据json字符串中的key来匹配对应实体类的属性，如果匹配一致且json中的该key对应的值
    符合(或可转换为)实体类的对应属性的类型要求时，会调用实体类的setter方法将值赋给该属性。
     */
    public void save(@RequestBody User user){
        userRepository.save(user);
    }

    @PutMapping("/update")
    public void update(@RequestBody User user){
        userRepository.update(user);

    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        userRepository.deleteById(id);
    }


}
