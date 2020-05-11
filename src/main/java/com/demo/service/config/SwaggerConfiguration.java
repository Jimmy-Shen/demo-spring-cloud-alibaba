package com.demo.service.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.demo.service.util.constant.Constants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 *
 * @author shenhongjun
 *
 */
@Component
@EnableSwagger2
@EnableKnife4j
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true")
public class SwaggerConfiguration implements EnvironmentAware, WebMvcConfigurer {
    private String basePackage;
    private String creatName;
    private String serviceName;
    private String description;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(this.serviceName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(this.basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(this.serviceName)
                .description(this.description)
                .contact(new Contact(this.creatName, "", ""))
                .version(Constants.CLIENT_VERSION)
                .build();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.basePackage = environment.getProperty("swagger.basepackage");
        this.creatName = environment.getProperty("swagger.service.developer");
        this.serviceName = environment.getProperty("swagger.service.name");
        this.description = environment.getProperty("swagger.service.description");
    }
}
