package com.system.service;

import com.system.mapper.UserMapper;
import com.system.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getuser(String id){
        return userMapper.getinfo(id);
    }
}
