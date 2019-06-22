package com.nlb.miaosha.Util;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShrioUtil {
   //FactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager ){
        ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //设置过滤器
        /**anon无需认证，直接访问
         * authc：需要认证才可以访问
         * user：使用rememberMe的功能才可以访问
         * perms：资源需要得到资源授权才可以访问
         * role：资源得到角色授权才可以访问
         */
         Map<String,String> map = new LinkedHashMap<>();
         map.put("/user/add","perms[add]");
         map.put("/user/update","perms[update]");
         map.put("/user/*","authc");
         shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
         shiroFilterFactoryBean.setLoginUrl("/loginIn1");
         shiroFilterFactoryBean.setUnauthorizedUrl("/Unauth");

        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    //创建Default
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shrioRealm") ShrioRealm shrioRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shrioRealm);
        return defaultWebSecurityManager;
    }
    //创建realm
    @Bean(name = "shrioRealm")
    public ShrioRealm getShrioRealm(){
        return new ShrioRealm();
    }

    //整合thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
