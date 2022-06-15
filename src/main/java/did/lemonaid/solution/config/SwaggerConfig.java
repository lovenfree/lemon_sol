package did.lemonaid.solution.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@Profile({"local","dev"})
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket solutionApi(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("did.lemonaid.solution.interfaces"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
          .tags(new Tag("Account","Account API"),
            new Tag("Trust Registry","Trust Register API"));

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("lemonaid Solution HTTP API")
                .version("V0.1")
                .build();
    }

}
