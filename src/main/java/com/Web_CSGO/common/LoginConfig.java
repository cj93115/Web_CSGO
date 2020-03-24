package com.Web_CSGO.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 
 * 
 * @Package: com.*.*.config 
 * @ClassName: LoginConfig 
 * @Description:拦截器配置
 * @author: zk
 * @date: 2019年9月19日 下午2:18:35
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new AdminInterceptor());
        //registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(
                                        "/**",
                                         "/LoginController/login",
                                         "RoomController/getRoomList",
                                         "/LoginController/loginPage", //登录
                                         "/LoginController/AdminloginPage", //登录
//                                        "/UserController/getUserPage", //登录
//                                         "/UserController/getUserList", //登录
                                         "/UserRegisterController/register", //注册
                                         "/drawImage",
                                         "/common/**",
                                         "/css/**",
                                         "/images/**",
                                         "/main/**",
                                         "/user/**",
                                         "/static/userimages/**",
                                         "/userimages/**",
                                         "/js/**",
                                         "/EasyUI-InsdepTheme/**",
                                         "/static",
                                         "/**/*.html",            //html静态资源
                                         "/**/*.js",              //js静态资源
                                         "/**/*.css",             //css静态资源
                                         "/**/*.woff",
                                         "/**/*.ttf",
                                         "*.html",            //html静态资源
                                         "*.js",              //js静态资源
                                         "*.css",             //css静态资源
                                         "*.woff2",             //css静态资源
                                         "*.otf",             //css静态资源
                                         "*.eot",             //css静态资源
                                         "*.svg",             //css静态资源
                                         "*.ttf"             //css静态资源
                                         );
    }
}