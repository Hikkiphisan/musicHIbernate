package org.example.hibernatemusic.configuration;




import org.example.hibernatemusic.service.HibernateMusicService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
@EnableWebMvc
@ComponentScan("org.example.hibernatemusic.controller")
public class AppConfiguration implements WebMvcConfigurer, ApplicationContextAware {
    // ... các code hiện tại giữ nguyên ...




    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    //// Template Resolver: Xác định vị trí các file HTML template
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }


    // Template Engine: Xử lý template sử dụng Template Resolver
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }


    // View Resolver: Liên kết Spring MVC với Thymeleaf để render view
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }


//    @Bean
//    public HibernateMusicService songService() {
//        // Tạo mới một đối tượng HibernateMusicService
//        return new HibernateMusicService();
//    }


    @Bean
    public HibernateMusicService songService() {
        return new HibernateMusicService();
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/css/**")
//                .addResourceLocations("/WEB-INF/css/");
//
//        registry.addResourceHandler("/js/**")
//                .addResourceLocations("/WEB-INF/js/");
//
//        registry.addResourceHandler("/images/**") //chạy đương dẫn nay thi anh xa den thu mục
//                .addResourceLocations("D:/images");
//    }
//
//    @Value("${file-upload}")
//    private String upload;
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/i/**")
//                .addResourceLocations("file:" + upload);
//        System.out.println(upload);
//    }
//
//
//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver getResolver() {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setMaxUploadSizePerFile(52428800);
//        return resolver;
//    }
}