package com.sgcc.config;

import com.sgcc.interceptor.ApiStatisticsInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig  extends WebMvcConfigurationSupport {
    @Bean
    public ApiStatisticsInterceptor setBean(){
        return new ApiStatisticsInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //注册自己的拦截器,并设置拦截路径，拦截多个可以全一个list集合
        registry.addInterceptor(setBean()).addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**","/webjars/**", "/v2/**", "/swagger-ui.html");
        super.addInterceptors(registry);
    }


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        String osname = System.getProperty("os.name");
        if( osname.toLowerCase().contains("win") )
        {
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/")
                    .addResourceLocations("classpath:/BOOT-INF/lib/");
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
        else
        {
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/")
                    .addResourceLocations("classpath:/BOOT-INF/lib/");
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }

    }


}
