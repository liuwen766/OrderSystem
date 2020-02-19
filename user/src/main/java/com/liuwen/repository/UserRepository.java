package com.liuwen.repository;

import com.liuwen.entity.User;

import java.util.List;

public interface UserRepository {
    public List<User> findAll(int index,int limit);
    public User findById(long id);
    public int count();
    public void save(User user);
    public void update(User user);
    public void deleteById(long id);

}
