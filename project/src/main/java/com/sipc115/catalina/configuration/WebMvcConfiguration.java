package com.sipc115.catalina.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/sipc115_resources/**").addResourceLocations("file:/Volumes/disk3/天津理工大学/程序员之路/115官网项目/sipc115_resources/");
        super.addResourceHandlers(registry);
    }

}
