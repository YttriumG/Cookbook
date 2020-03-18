package com.system.Controller;

import com.system.pojo.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
public class indexController {
    @GetMapping("/welcome")
    public String loginsucceed(LoginUser user,
                        Model model,
                        HttpServletRequest request){
        HttpSession session = request.getSession();
        user.setDisplay_name((String) session.getAttribute("nickname"));
        user.setLogin_id((String) session.getAttribute("name"));
        model.addAttribute("nickname",session.getAttribute("nickname"));
        model.addAttribute("name",session.getAttribute("name"));

        //输出Session内容
        for (Enumeration e = session.getAttributeNames();e.hasMoreElements();){
            String sessionName = (String) e.nextElement();
            System.out.println(sessionName + ":" + session.getAttribute(sessionName));
        }
        return "index";
    }

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }


}
