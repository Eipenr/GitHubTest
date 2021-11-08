package com.qianfeng.smartdevices.controller.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2//开启 swagger2
@Configuration
public class SwaggerConfig {

    //主要配置的就是我们想要处理哪些接口，将哪些接口穿换成在线文档，主要是设置 controller 的包
    @Bean
    public Docket docket(ApiInfo apiInfo){
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.qianfeng.smartdevices.controller"))
                .build().apiInfo(apiInfo);
    }


    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("智能家具管理在线文档")
                .contact(new Contact("Eipen","http://www.baidu.com","eipena@163.com"))
                .description("这是一个摘要")
                .license("许可证")
                .licenseUrl("http://www.baidu.com")
                //版本
                .version("1.1.1")
                .build();
    }

}
