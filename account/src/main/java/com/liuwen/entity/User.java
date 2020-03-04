package com.liuwen.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    //命名和表格信息一致，不用在xml文件中转化
    private long id;
    private String username;
    private String password;
    private String nickname;
    private String gender;
    private String telephone;
    private Date registerdate;
    private String address;
}
