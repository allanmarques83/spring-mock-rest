package io.github.allanmarques83;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class EnableRestMockConfig implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] { EnableRestMockConfigBean.class.getName() };
    }
}

@Configuration
class EnableRestMockConfigBean {
    @Bean
    public WebMvcConfigurer get() {
        return new RestInterceptorConfig();
    }
}