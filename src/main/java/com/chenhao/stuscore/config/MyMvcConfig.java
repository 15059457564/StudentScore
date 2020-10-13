package com.chenhao.stuscore.config;

import com.chenhao.stuscore.conponent.LoginHandlerIntercept;
import com.chenhao.stuscore.conponent.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ch
 * @date 2020/8/28
 **/
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
//添加拦截器  防止直接进入主页
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
        registry.addInterceptor(new LoginHandlerIntercept()).addPathPatterns("/**")
                .excludePathPatterns("/login","/","/Login.do","/yzm","/static/**","/webjars/**","/css/**","/font-awesome/**","/js/**","/fonts/**","/img/**","/plugins/**");
//                    .excludePathPatterns("/login","/","Login.do","yzm","/static/**");
    }

}
