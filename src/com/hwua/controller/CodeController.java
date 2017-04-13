package com.hwua.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hwua.util.VerifyCodeUtils;

@Controller
@RequestMapping("/codeController")
public class CodeController {

	@RequestMapping(value="/code") //
	public void getCode(HttpServletResponse response,HttpSession session) {
		
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
          
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
          
        session.setAttribute("code", verifyCode.toLowerCase());
        System.out.println(verifyCode);
        //生成图片  
        int w = 80, h = 30;  
        try {
        	
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  
    }  
    
}
