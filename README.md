
## Shiro实现基本流程
1. 获取当前的Subject.调用SecurityUtills.getSubject();
2. 测试当前的用户已经被认证，即是否已经登录，调用Subject的isAuthenticated()
3.  若没有被认证，则把用户名和密码封装为UsernamePasswordToken 对象
- 创建一个表单
- 把请求提交到Springmvc的handler
- 获得用户和密码
4. 执行登录：调用Subjec的login(token) 方法
5. 自定义realm方法，从数据库中获取对应的记录，返回给shiro
-  实际上需要继承org.apache.shiro.realm.AuthenticationToken 类
-  实现doGetAuthenticationInfo(AuthenticationToken var1) 方法
-  由shiro完成对密码的比对
 

## 密码的比对通过的方式：
是通过 CredentialsMatcher接口的doCredentialsMatch方法来进行的密码的比对，**匹配关键部分源码**
```java

    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
        CredentialsMatcher cm = getCredentialsMatcher();
        if (cm != null) {
            if (!cm.doCredentialsMatch(token, info)) {
                //not successful - throw an exception to indicate this:
                String msg = "Submitted credentials for token [" + token + "] did not match the expected credentials.";
                throw new IncorrectCredentialsException(msg);
            }
        } else {
            throw new AuthenticationException("A CredentialsMatcher must be configured in order to verify " +
                    "credentials during authentication.  If you do not wish for credentials to be examined, you " +
                    "can configure an " + AllowAllCredentialsMatcher.class.getName() + " instance.");
        }
    }

```
## 为什么使用MD5 盐值加密Salt 
相同的密码就会有相同的MD5算法值
 需要加盐值salt 使算法值不相同
 ## 如何设置加盐
1. 如何做到 doGetAuthenticationInfo()方法返回值创建 SimpleAuthenticationInfo 对象的时候，需要使用SimpleAuthenticationInfo(credentials, credentials, credentialsSalt, realmName)构造方法
2. 使用ByteSource.Util.bytes()来计算盐值; 我们使用用户名 一般用户名是不能重复的。
3. 盐值要唯一：一般使用随机字符串或者userid 
4. 使用new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);来计算盐值加密后的密码的值
```java
     String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("user");//加盐
        int hashIterations = 1000;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);//实现加密算法。
        System.out.println(result);
```



## 如何把一个字符串加密为MD5
替换当前Realm的credentialsMacher 属性，直接使用HashedCredentialsMacher 对象，并设置加密算法即可。这里需要在spring配置文件中设置
```xml
    <!--配置realm-->
    <bean id="jdbcRealm" class="com.hjsjy.shiro.ShiroRealm">
        <property name="credentialsMatcher">
            <bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
                <!--设置加密的类型 MD5-->
                <property name="hashAlgorithmName" value="MD5"/>
                <!--设置加密的次数 1000次-->
                <property name="hashIterations" value="1024"/> 
            </bean>
        </property>
    </bean>
```




