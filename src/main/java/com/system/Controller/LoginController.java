package com.system.Controller;

import com.system.mapper.loginMapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.system.Util.RamdomValidateCodeUtil;
import com.system.pojo.User;
import com.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public String tologin(
            @RequestParam(value = "username", required=false) String loginid,
            @RequestParam(value = "password", required=false) String password,
            HttpServletRequest request,
            Model model) {
        HttpSession session = request.getSession();
        User user = loginService.loginUser(loginid,password);
        if (user == null) {
            model.addAttribute("msg","用户名或密码错误！请重新输入");
            return "index";
        }else{
            session.setAttribute("id",user.getUser_id());
            session.setAttribute("nickname",user.getDisplay_name());
            model.addAttribute("Loginuser",user);
            return "redirect:/welcome";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        System.out.println("用户注销！");
        session.invalidate();
        return "redirect:/index";
    }

    @RequestMapping("/register")
    public String toregister(){
        return "register";
    }

    //验证码生成类
    @Resource
    private DefaultKaptcha captchaProducer;

    public static final String LOGIN_VALIDATE_CODE = "login_validate_code";

    @RequestMapping("/loginValidateCode")
    public void loginValidateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        RamdomValidateCodeUtil.validateCode(request,response,captchaProducer,LOGIN_VALIDATE_CODE);
    }

    //https://www.cnblogs.com/demodashi/p/10503408.html
    @RequestMapping("/newregister")
    public String Register(HttpServletRequest request,
                           @RequestParam("validateCode") String validateCode,
                           @RequestParam("inputPassword1") String password,
                           @RequestParam("inputPassword2") String password1,
                           @RequestParam("inputPhone") String login_id,
                           @RequestParam("inputName") String display_name,
                           Model model){
        //获取系统验证码字符
        String loginValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        System.out.println(validateCode);
        System.out.println(password);
        System.out.println(password1);
        System.out.println(login_id);
        System.out.println(display_name);
        System.out.println(loginValidateCode);
        if (loginValidateCode == null) {
            model.addAttribute("msg","验证码过期，请重新输入");
            return "register";
        }else if (!loginValidateCode.equals(validateCode)){
            model.addAttribute("msg","验证码错误，请重新输入");
            return "register";
        }else{
            if (!password.equals(password1)){
                model.addAttribute("msg","两次密码输入不一样，请重新输入");
                return "register";
            }else{
                System.out.println("new User!");
                User user = new User();
                user.setUser_tel(login_id);
                user.setDisplay_name(display_name);
                user.setUser_password(password);
                user.setUser_privilege(1);
                loginService.addUser(user);
                HttpSession session = request.getSession();
                session.setAttribute("nickname",display_name);
                session.setAttribute("tel",login_id);
                return "redirect:/welcome";
            }
        }


    }
}
