package com.system.Controller;


import com.system.mapper.recipeMapper;
import com.system.pojo.User;
import com.system.pojo.recipe;
import com.system.service.recipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class publishController {
    @Autowired
    private recipeMapper recipeMapper;

    @Autowired
    private recipeService recipeService;

    @GetMapping("/publish/{id}")
    public String edit(
            @PathVariable("id")Integer id,
            Model model,
            HttpServletRequest request
            ){

        //持久化登录
        HttpSession session = request.getSession();
        model.addAttribute("nickname",session.getAttribute("nickname"));
        model.addAttribute("id",session.getAttribute("id"));

        recipe recipe = recipeService.selectByID(id);

        session.setAttribute("recipe_id",recipe.getId());
        session.setAttribute("recipe_create",recipe.getCreate_time());
        model.addAttribute("recipe_name",recipe.getName());
        model.addAttribute("recipe_pic",recipe.getPic_url());
        model.addAttribute("recipe_type",recipe.getType());
        model.addAttribute("recipe_detail",recipe.getDescription());
        model.addAttribute("recipe_material",recipe.getMaterial());
        model.addAttribute("recipe_step",recipe.getStep());
        model.addAttribute("recipe_trick",recipe.getTrick());

        return "publish_recipes";
    }

    @RequestMapping("/pub_ques")
    public String pub_ques(){
        return "publish_question";
    }

    @RequestMapping("/pub_rec")
    public String enter(){
        return "publish_recipes";
    }

    @PostMapping("/publish")
    public String newrecipes(@RequestParam(required = false, value = "recipe_name") String name,
                             @RequestParam(required = false, value = "recipe_pic") String pic,
                             @RequestParam(required = false, value = "recipe_type") String type,
                             @RequestParam(required = false, value = "recipe_detail") String detail,
                             @RequestParam(required = false, value = "recipe_material") String material,
                             @RequestParam(required = false, value = "recipe_step") String step,
                             @RequestParam(required = false, value = "recipe_trick") String trick,
                             Model model,
                             HttpServletRequest request){

        //持久化登录
        HttpSession session = request.getSession();
        model.addAttribute("nickname",session.getAttribute("nickname"));
        model.addAttribute("id",session.getAttribute("id"));


        model.addAttribute("recipe_name",name);
        model.addAttribute("recipe_pic",pic);
        model.addAttribute("recipe_type",type);
        model.addAttribute("recipe_detail",detail);
        model.addAttribute("recipe_material",material);
        model.addAttribute("recipe_step",step);
        model.addAttribute("recipe_trick",trick);

        if (name == "" || name == null){
            model.addAttribute("error","菜谱名称不能为空！");
            return "publish_recipes";
        }
        if (pic == "" || pic == null){
            model.addAttribute("error","图片信息不能为空！");
        }
        if (type == "" || type == null){
            model.addAttribute("error","菜谱种类不能为空！");
            return "publish_recipes";
        }
        if (detail == "" || detail == null){
            model.addAttribute("error","菜谱描述不能为空！");
            return "publish_recipes";
        }
        if (material == "" || material == null){
            model.addAttribute("error","所需材料不能为空！");
            return "publish_recipes";
        }
        if (step == "" || step == null){
            model.addAttribute("error","烹饪步骤不能为空！");
            return "publish_recipes";
        }

        //更新菜谱信息
        if (session.getAttribute("recipe_id")!=null) {
            int recipe_id = (int) session.getAttribute("recipe_id");
            recipe old_recipe = recipeService.selectByID(recipe_id);

            //更新菜谱信息
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = tempDate.format(new Date());

            old_recipe.setName(name);
            old_recipe.setPic_url(pic);
            old_recipe.setType(type);
            old_recipe.setDescription(detail);
            old_recipe.setMaterial(material);
            old_recipe.setStep(step);
            old_recipe.setLast_modified_time(datetime);
            recipe new_recipe = recipeService.update(old_recipe);
            User user = recipeService.getUser(recipe_id);
            List<String> retype = recipeService.type(recipe_id);

            model.addAttribute("recipe", new_recipe);
            model.addAttribute("user",user);
            model.addAttribute("type",retype);
            session.removeAttribute("recipe_id");

            return "recipe";
        }
        //提交新菜谱
        else{
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = tempDate.format(new Date());
            recipe recipe = new recipe();
            recipe.setName(name);
            recipe.setPic_url(pic);
            recipe.setType(type);
            recipe.setDescription(detail);
            recipe.setMaterial(material);
            recipe.setStep(step);
            recipe.setCreate_time(datetime);
            recipe.setLast_modified_time(datetime);
            recipe.setComments(0);
            recipe.setViews(0);
            recipe.setLikes(0);
            recipe.setAuthor((int)session.getAttribute("id"));

            recipeService.create(recipe);
            model.addAttribute("recipe",recipe);
            return "recipe";

        }
    }



}
