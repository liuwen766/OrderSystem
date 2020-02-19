package com.liuwen.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {
    //返回layui可以接收的数据类型
    private int code;
    private String msg;
    private int count;
    private List<User> data;
}
