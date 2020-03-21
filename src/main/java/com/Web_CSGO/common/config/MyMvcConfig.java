package com.Web_CSGO.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
        String path = System.getProperty("user.dir");
        String path2 = path.replace("\\", "/");
        System.out.println("-------------------------");
        System.out.println(path);
        registry.addResourceHandler("/csgo_img/**").addResourceLocations("file:D:/csgo_img/");
    }



}
