package io.github.allanmarques83;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.github.allanmarques83.RestInterceptor;

@Configuration
@EnableWebMvc
public class RestInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("passou aqui");
        registry.addInterceptor(new RestInterceptor());
    }
}
