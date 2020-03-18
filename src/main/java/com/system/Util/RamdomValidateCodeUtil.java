package com.system.Util;

import com.google.code.kaptcha.impl.DefaultKaptcha;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;


public class RamdomValidateCodeUtil {
    public static void validateCode(HttpServletRequest request,
                                    HttpServletResponse response,
                                    DefaultKaptcha captchaProducer,
                                    String ValidateSessionKey) throws Exception{
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control","post-check=0, pre-check=0");
        response.setHeader("Pragma","no-cache");

        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();

        request.getSession().setAttribute(ValidateSessionKey,capText);

        BufferedImage bi = captchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        ImageIO.write(bi,"jpg",out);
        try{
            out.flush();
        }finally {
            out.close();
        }
    }
}
