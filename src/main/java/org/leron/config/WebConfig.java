package org.leron.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    private static final String UPLOAD_DIR = "uploads/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/" + UPLOAD_DIR + "**")
                .addResourceLocations("file:" + UPLOAD_DIR);
    }
}