package com.system.Controller;

import com.system.mapper.recipeMapper;
import com.system.service.recipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class publishController {
    @Autowired
    private recipeMapper recipeMapper;

    @Autowired
    private recipeService recipeService;

    @RequestMapping("/pub_rec")
    public String enter(){
        return "publish_recipes";
    }

    @PostMapping("/publish")
    public String newrecipes(@RequestParam(required = false, value = "recipe_name") String name,
                             @RequestParam(required = false, value = "recipe_type") String type,
                             @RequestParam(required = false, value = "recipe_detail") String detail,
                             @RequestParam(required = false, value = "recipe_material") String material,
                             @RequestParam(required = false, value = "recipe_step") String step,
                             @RequestParam(required = false, value = "recipe_trick") String trick,
                             Model model){
        model.addAttribute("recipe_name",name);
        model.addAttribute("recipe_type",type);
        model.addAttribute("recipe_detail",detail);
        model.addAttribute("recipe_material",material);
        model.addAttribute("recipe_step",step);
        model.addAttribute("recipe_trick",trick);

        if (name == "" || name == null){
            model.addAttribute("error","菜谱名称不能为空！");
            return "publish_recipes";
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
        String a = "名称："+name+"\n种类："+type+"\n描述："+detail+"\n材料："+material+"\n步骤："+step+trick;
        System.out.println(a);

        model.addAttribute("recipe",a);
        return "publish_recipes";

    }



}
