package io.github.allanmarques83;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class EnableRestMockConfig implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("passou aqui0");

        return new String[] { DotEnvConfigurationDefault.class.getName() };
    }
}

@Configuration
class DotEnvConfigurationDefault {
    @Bean
    public WebMvcConfigurer get() {
        System.out.println("passou aqui00");

        return new RestInterceptorConfig();
    }
}