package com.hjsjy.shiro.services;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author hjsjy
 * @create 2018/10/16
 * @since 1.0.0
 */
public class ShiroService {
    @RequiresRoles("admin")
    public void testMethod(){
        System.out.println("time:" + new Date());
        Session session = SecurityUtils.getSubject().getSession();
        Object key = session.getAttribute("key");
        System.out.println("Services Sessionkey"+ key);

    }

}