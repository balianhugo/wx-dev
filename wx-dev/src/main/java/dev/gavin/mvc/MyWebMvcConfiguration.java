package dev.gavin.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springboot mvc 相关配置
 */
@Configuration
public class MyWebMvcConfiguration implements WebMvcConfigurer {

   @Bean
   public AppGlobalInterceptor getAppGlobalInterceptor(){
      return new AppGlobalInterceptor();
   }

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {

   }

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(getAppGlobalInterceptor())
              .addPathPatterns("/**")
              .excludePathPatterns("/error")
              .excludePathPatterns("/error/**")
              .excludePathPatterns("/static/**")
              .excludePathPatterns("/wxauth/**");
   }
}
