package did.lemonaid.solution.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
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
//                .paths(Predicates.not(PathSelectors.ant("/api/**/file/**")))
//                .paths(Predicates.not(PathSelectors.ant("/api/**/account/**")))

                .build()
                .apiInfo(apiInfo());
//
//                .groupName("02.RDIS API")
//                .directModelSubstitute(LocalDateTime.class, String.class)
//                .tags(
//                        new Tag[] {
//                                new Tag("Tenant", "Tenant Management API"),
//                                new Tag("Certificates", "Issuable Certificates API"),
//                                new Tag("FAQ", "FAQ API"),
//                                new Tag("NOTICE", "NOTICE API")})
//                .enable(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Lemonade HTTP API")
                .version("V0.1")
                .build();
    }

}
