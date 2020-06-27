package com.system.Controller;

import com.system.pojo.User;
import com.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CenterController {

    @Autowired
    public UserService userService;

    @RequestMapping("/User/{id}")
    public String enter(
            @PathVariable("id")String id,
            Model model,
            HttpServletRequest request){
        HttpSession session = request.getSession();
        model.addAttribute("nickname",session.getAttribute("nickname"));
        model.addAttribute("id",session.getAttribute("id"));
        User user = userService.getuser(id);
        model.addAttribute("user",user);


        return "Personal_center";
    }
}
