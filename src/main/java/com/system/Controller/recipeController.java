package com.system.Controller;

import com.system.pojo.LoginUser;
import com.system.pojo.User;
import com.system.pojo.recipe;
import com.system.service.recipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class recipeController {


    @Autowired
    private recipeService recipeService;

    @GetMapping("/recipe/{id}")
    public String recipe(@PathVariable("id") int id, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute("user");
        recipe recipe = recipeService.selectByID(id);
        List<String> type = recipeService.type(id);
        User user = recipeService.getUser(id);
        System.out.println(recipe);
        if (recipe == null){
            return "404";
        }else{
            model.addAttribute("recipe",recipe);
            model.addAttribute("type",type);
            model.addAttribute("user",user);
            return "recipe";
        }
    }

    @PostMapping("/material")
    @ResponseBody
    public String material(int id){
        String info;

        return "info";
    }


}
