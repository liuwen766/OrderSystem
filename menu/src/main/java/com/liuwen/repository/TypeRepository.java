package com.liuwen.repository;

import com.liuwen.entity.Type;

import java.util.List;

public interface TypeRepository {

    public Type findById(long id);
    public List<Type> findAll();
}
