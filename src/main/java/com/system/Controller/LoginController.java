package com.system.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.objects.Global;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {
    @RequestMapping("/login")
    @ResponseBody
    public String tologin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {
        return "Success";
    }
}
