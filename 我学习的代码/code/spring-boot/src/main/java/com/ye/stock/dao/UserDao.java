package com.ye.stock.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


public interface UserDao {
    public List findUser();

}