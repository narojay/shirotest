package com.hjsjy.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author hjsjy
 * @create 2018/10/15
 * @since 1.0.0
 */
public class SecondRealm extends AuthenticatingRealm {



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("SecondRealm doGetAuthenticationInfo");
        //1. AuthenticationToken转化为 UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //2. 从usernamePasswordToken 中获取用户名
        String username = usernamePasswordToken.getUsername();
        //3. 调用数据库的方法 从数据库中查询username对应的用户记录
        System.out.println("从数据库中获取username" + username + "所对应的信息");
        //4. 若用户不存在，则可以抛出UnknownaccountException异常
        if ("unknown".equals(username)) {
            throw new UnknownAccountException("用户不存在");
        }
        //5. 根据用户信息的情况，决定是否需要抛出其他的AuthenticationException异常
        if ("monster".equals(username)) {
            throw new LockedAccountException("用户被锁定");
        }
        //6.根据用户的情况，来构建authenticationInfo 对象并返回
        //1) principals 认证的实体信息，可以是username 也可以是数据表对应的用户的实体类对象
        Object principals = username;
        //2） 从credentials：密码，
        Object credentials = "null";
        if ("admin".equals(username)) {
            credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";

        } else if ("user".equals(username)) {
            credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
        }

        //3） realmName 当前realm对象的name，调用父类的getName()方法即可
        String realmName = getName();
        //4) 盐值 salt
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        AuthenticationInfo  simpleAuthenticationInfo = null;
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, credentials, credentialsSalt, realmName);
            return simpleAuthenticationInfo;
    }


}
