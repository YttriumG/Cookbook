package com.system.mapper;

import com.github.pagehelper.Page;
import com.system.pojo.recipe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface recipeMapper {
    int create_recipe(recipe recipe);
    int update_recipe(recipe recipe);
    Page<recipe> get_all_recipes();

    @Select("select count(1) from recipes")
    int count();

}
