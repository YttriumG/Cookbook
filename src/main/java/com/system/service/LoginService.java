package com.system.service;

import com.system.mapper.loginMapper;
import com.system.pojo.LoginUser;
import com.system.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private loginMapper loginMapper;

    //用户登录
    public User loginUser(String id,String pwd){
        User u = loginMapper.loginUser(id,pwd);
        if (u == null){
            return null;
        }else{
            return u;
        }

    };
    //根据ID查找用户
    public User selectUser(String id){
        return loginMapper.selectUser(id);
    };

    //根据昵称查找用户
    public  User selectUserByName(String display_name){
        return loginMapper.selectUserbyName(display_name);
    }

    //新用户注册
    public int addUser(User user){
        return loginMapper.addUser(user);
    };
    //更新用户数据
    public int updateUser(User user){
        return loginMapper.updateUser(user);
    };
    //查找所有用户
    public List<User> findAllUser(){
        List<User> users = new ArrayList<>();
        users = loginMapper.findAllUser();
        return users;
    };

}
