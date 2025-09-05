package org.leron;

import org.leron.service.RoleService;
import org.leron.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class LeronApplication implements WebMvcConfigurer
{

    public static void main(String[] args)
    {

        SpringApplication.run(LeronApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:./uploads/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
    }

    @Bean
    public CommandLineRunner run(RoleService roleService, UserService userService)
    {
        return args ->
        {
            roleService.createDefaultRolesIfNotExist();
            userService.createAdminUserIfNotExist("admin", "admin", "admin@example.com");
            System.out.println("Default roles and admin user checked/created.");
        };
    }
}
