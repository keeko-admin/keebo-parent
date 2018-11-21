package com.keeko.imovie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 * web 配置类
 */
@Configuration
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setCache(false);
        resolver.setPrefix("/WEB-INF/view");
        resolver.setSuffix(".html");
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setExposeContextBeansAsAttributes(true);
        resolver.setOrder(1);
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


   /* @Bean
    public BeetlSpringViewResolver viewResolver(){
        BeetlSpringViewResolver resolver = new BeetlSpringViewResolver();
        //resolver.setCache(false);
        //resolver.setPrefix("/WEB-INF/view");
        //resolver.setSuffix(".html");
        resolver.setContentType("text/html;charset=UTF-8");
        //resolver.setExposeContextBeansAsAttributes(true);
        resolver.setOrder(0);
        return resolver;
    }*/
}
