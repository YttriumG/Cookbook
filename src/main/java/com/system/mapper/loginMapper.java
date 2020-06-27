package com.system.mapper;


import com.system.pojo.LoginUser;
import com.system.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface loginMapper{
    //用户登录
    User loginUser(String user_tel,String user_password);
    //根据ID查找用户
    User selectUser(String id);
    //根据用户名查找用户
    User selectUserbyName(String display_name);
    //新用户注册
    int addUser(User user);
    //更新用户数据
    int updateUser(User user);
    //查找所有用户
    List<User> findAllUser();
}
