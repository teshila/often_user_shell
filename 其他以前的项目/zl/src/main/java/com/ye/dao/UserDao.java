package com.ye.dao;

import java.util.Map;

import com.ye.pojo.User;

public interface UserDao {

	public void save(User user);

	public User getUserIsExit(Map map);

}
