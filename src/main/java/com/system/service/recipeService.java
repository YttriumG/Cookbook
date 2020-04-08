package com.system.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.mapper.recipeMapper;
import com.system.pojo.recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class recipeService {
    @Autowired
    private recipeMapper recipeMapper;

    public List<recipe> list(int page, int size){
        PageHelper.startPage(page, size);
        List<recipe> list = recipeMapper.get_all_recipes();
            System.out.println("分页数据："+list);
        return list;
    }
}
