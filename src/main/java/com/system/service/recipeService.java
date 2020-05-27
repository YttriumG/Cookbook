package com.system.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.mapper.recipeMapper;
import com.system.pojo.User;
import com.system.pojo.recipe;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class recipeService {
    @Autowired
    private recipeMapper recipeMapper;

    public List<recipe> list(int page, int size){
        PageHelper.startPage(page, size);
        List<recipe> list = recipeMapper.get_all_recipes();
        //System.out.println("分页数据："+list);
        return list;
    }

    public List<recipe> popular(){
        List<recipe> popular = recipeMapper.getPopular();
        return popular;
    }

    public List<recipe> latest(){
        List<recipe> latest = recipeMapper.getLatest();
        return latest;
    }

    //获取菜谱相应信息
    public recipe selectByID(int id){
        recipe recipe = recipeMapper.selectById(id);
        return recipe;
    }

    //获取菜谱标签
    public List type(int id){
        recipe recipe = recipeMapper.selectById(id);
        String type = recipe.getType();
        if (type != null) {
            String group[] = type.split(" ");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < group.length; i++) {
                list.add(group[i]);
            }
            return list;
        }else return null;
    }

    //获取菜谱食材
    public List<List> main_material(int id){
        recipe recipe = recipeMapper.selectById(id);
        String jsonstr = recipe.getMaterial();
        List<List> material = new ArrayList<>();
        if (jsonstr != null){
            JSONObject jsonObject = JSONObject.fromObject(jsonstr);
        }
        return material;
    }

    //获取上传菜谱的用户
    public User getUser(int id){
        User user = recipeMapper.getUser(id);
        System.out.println(user);
        return user;
    }
}
