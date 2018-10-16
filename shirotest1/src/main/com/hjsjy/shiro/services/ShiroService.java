package com.hjsjy.shiro.services;

import org.apache.shiro.authz.annotation.RequiresRoles;

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
    }
}