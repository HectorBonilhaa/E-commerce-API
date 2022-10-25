package br.com.serratec.eCommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }
    
	private ApiInfo apiInfo(){
		ApiInfo apiInfo = new ApiInfoBuilder()
		.title("API trabalho final grupo 1")
		.description("Essa é uma API desenvolvida para o trabalho final do grupo 1")
		.license("Apache License Version 2.0")
		.licenseUrl("https://www.apache.org/license/LICENSE-2.0")
		.version("1.0.0")
		.contact(new Contact("Serratec","www.serrtatec.org.br", "grupo1serratec5@gmail.com"))
		.build();
		return apiInfo;
		}

}