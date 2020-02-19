package com.liuwen.repository;

import com.liuwen.entity.Admin;

public interface AdminRepository {
    public Admin login(String username,String password);
}
