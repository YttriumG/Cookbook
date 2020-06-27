package com.system.mapper;

import com.github.pagehelper.Page;
import com.system.pojo.User;
import com.system.pojo.recipe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface recipeMapper {
    int create_recipe(recipe recipe);
    int update_recipe(int id,String name,String pic,String type,String description,String material,String step,String trick,String last_modified_time);
    Page<recipe> get_all_recipes();

    @Select("select count(1) from recipes")
    int count();
    List<recipe> getPopular();
    List<recipe> getLatest();

    recipe selectById(int id);
    User getUser(int id);

}
