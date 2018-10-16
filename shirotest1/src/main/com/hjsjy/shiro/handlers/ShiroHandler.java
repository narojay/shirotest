package com.hjsjy.shiro.handlers;

import com.hjsjy.shiro.services.ShiroService;
import org.apache.commons.logging.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author hjsjy
 * @create 2018/10/15
 * @since 1.0.0
 */
@Controller
@RequestMapping("/shiro")
public class ShiroHandler {
    @Autowired
    private ShiroService shiroService;
    @RequestMapping("/testShiroAnnotaion")
    public  String testShiroAnnotaion(){
        shiroService.testMethod();
        return "redirect:/index.jsp";
    }
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password)  {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username,
                    password);
            token.setRememberMe(true);
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                System.out.println("登录失败：" + e.getMessage());
            }
        }
        return "redirect:/index.jsp";
    }
}