package com.liuwen.repository;

import com.liuwen.entity.User;

public interface UserRepository {
    public User login(String username, String password);

}
