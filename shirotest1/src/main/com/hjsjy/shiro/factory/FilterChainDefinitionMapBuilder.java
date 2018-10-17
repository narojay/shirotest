package com.hjsjy.shiro.factory;

import java.util.LinkedHashMap;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author hjsjy
 * @create 2018/10/17
 * @since 1.0.0
 */
public class FilterChainDefinitionMapBuilder {
            public LinkedHashMap<String,String> buildFilterChainDefinitionMap(){
                LinkedHashMap<String, String> map = new LinkedHashMap<>();
                /*        /login.jsp= anon
                /shiro/login= anon
                /shiro/logout = logout

                /user.jsp = roles[user]
                /admin.jsp = roles[admin]
                # everything else requires authentication:

                /** = authc*/
                map.put("/login.jsp", "anon");
                map.put("/shiro/login", "anon");
                map.put("/shiro/logout", "logout");
                map.put("/user.jsp", "roles[user]");
                map.put("/admin.jsp", "roles[admin]");
                map.put("  /**", "authc");

                return map;
            }
}