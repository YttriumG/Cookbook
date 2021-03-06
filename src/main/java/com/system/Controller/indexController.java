package com.system.Controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.system.pojo.LoginUser;
import com.system.pojo.recipe;
import com.system.service.recipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

@Controller
public class indexController {
    @Autowired
    private recipeService recipeService;

    @GetMapping({"/","/index","/welcome"})
    public String loginsucceed(Model model,
                               HttpServletRequest request,
                               @RequestParam(name = "page", defaultValue = "1") int page,
                               @RequestParam(name = "size", defaultValue = "3") int size){
        //用户登录模块
        HttpSession session = request.getSession();
        String nickname = (String) session.getAttribute("nickname");
        Integer id = (Integer) session.getAttribute("id") ;
        model.addAttribute("nickname",nickname);
        model.addAttribute("id",id);

            //输出Session内容
            for (Enumeration e = session.getAttributeNames();e.hasMoreElements();){
                String sessionName = (String) e.nextElement();
                System.out.println(sessionName + ":" + session.getAttribute(sessionName));
            }


        //菜谱信息模块
        if (page <= 0) {
            page = 1;
        }
        System.out.println("当前页是"+page+";显示条数是"+size);

        //返回数据库中的已存菜谱
        List<recipe> recipes = recipeService.list(page,size);
        PageInfo<recipe> pageInfo = new PageInfo<recipe>(recipes, 5);
        System.out.println(recipes);
        model.addAttribute("recipes",recipes);
        model.addAttribute("pageInfo",pageInfo);

        //最热菜谱
        List<recipe> popular = recipeService.popular();
        model.addAttribute("popular",popular);
        System.out.println("最热菜谱"+popular);

        //最新菜谱
        List<recipe> latest = recipeService.latest();
        model.addAttribute("latest", latest);
        System.out.println("最新菜谱"+latest);
        return "index";
    }



}
