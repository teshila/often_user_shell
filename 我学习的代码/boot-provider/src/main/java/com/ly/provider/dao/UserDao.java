package com.ly.provider.dao;

import com.ly.provider.pojo.User;

import java.util.List;

public interface UserDao {

    public List<User> getUsers(String uid);
}
