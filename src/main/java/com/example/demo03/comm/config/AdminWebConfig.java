package com.example.demo03.comm.config;

import com.example.demo03.comm.intercepter.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/7/19 22:31
 * @describe Web配置
 * @since 1.8
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    @Autowired
    private Interceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/**", "/test/**", "/zhouyuanjie/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/doc.html", "/favicon.ico");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/zhouyuanjie/images/resources/**").addResourceLocations("file:/data/upload/images/");
        // registry.addResourceHandler("/zhouyuanjie/file/resources/**").addResourceLocations("file:/data/upload/files/");
        registry.addResourceHandler("/zhouyuanjie/images/resources/**").addResourceLocations("file:/Users/eternalcoder/Desktop/upload/images/");
        registry.addResourceHandler("/zhouyuanjie/file/resources/**").addResourceLocations("file:/Users/eternalcoder/Desktop/upload/files/");
        registry.addResourceHandler("swagger-ui.html");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}