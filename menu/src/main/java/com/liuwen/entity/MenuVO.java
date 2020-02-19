package com.liuwen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO {
    //返回layui可以接收的数据类型
    private int code;
    private String msg;
    private int count;
    private List<Menu> data;
}
