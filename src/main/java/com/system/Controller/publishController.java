package com.system.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class publishController {
    @RequestMapping("/pub_rec")
    public String enter(){
        return "publish_recipes";
    }
}
