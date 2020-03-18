package com.system.mapper;


import com.system.pojo.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface loginMapper{
    //用户登录
    LoginUser loginUser(LoginUser user);
    //根据ID查找用户
    LoginUser selectUser(String id);
    //新用户注册
    int addUser(LoginUser user);
    //更新用户数据
    int updateUser(LoginUser user);
    //查找所有用户
    List<LoginUser> findAllUser();
}
