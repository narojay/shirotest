package com.hjsjy.shiro.handlers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) throws UnsupportedEncodingException {
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
//             执行登录
                subject.login(token);
            }catch (AuthenticationException ae){
                System.out.println("登录失败："+ae.getMessage());
            }
        }
        return "redirect:/index.jsp";
    }
}