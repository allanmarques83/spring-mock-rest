package io.github.allanmarques83;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class RestInterceptorConfig implements WebMvcConfigurer {
    @Autowired RestInterceptor restInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(restInterceptor).order(Ordered.LOWEST_PRECEDENCE);
    }
}
