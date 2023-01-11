package com.tencent.mall.config;

import com.tencent.mall.interceptor.SysInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jarry
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }

    @Bean
    public SysInterceptor sysInterceptor() {
        return new SysInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = new String[]{"/adminLogin", "/product/**", "/bigType/**", "/users/wxlogin", "/weixinpay/**"};
        registry.addInterceptor(sysInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/swiper/**").addResourceLocations("file:D:\\java1234-mall\\swiperImgs\\");
        registry.addResourceHandler("/image/bigType/**").addResourceLocations("file:D:\\java1234-mall\\bigTypeImgs\\");
        registry.addResourceHandler("/image/product/**").addResourceLocations("file:D:\\java1234-mall\\productImgs\\");
        registry.addResourceHandler("/image/productSwiperImgs/**").addResourceLocations("file:D:\\java1234-mall\\productSwiperImgs\\");
        registry.addResourceHandler("/image/productIntroImgs/**").addResourceLocations("file:D:\\java1234-mall\\productIntroImgs\\");
        registry.addResourceHandler("/image/productParaImgs/**").addResourceLocations("file:D:\\java1234-mall\\productParaImgs\\");
    }


}
