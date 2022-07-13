package did.lemonaid.solution.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SwaggerConfig {

  @Bean
   public GroupedOpenApi publicApi() {
       return GroupedOpenApi.builder()
               .group("lemonaid- Trust Registry")
               .pathsToMatch("/v1/trust-registry/**")
               .build();
   }

  @Bean
  public GroupedOpenApi solutionRegistryApi() {
    return GroupedOpenApi.builder()
      .group("lemonaid- Solution Registry")
      .pathsToMatch("/v1/solution-registry/**")
      .build();
  }

   @Bean
   public GroupedOpenApi adminApi() {
       return GroupedOpenApi.builder()
               .group("lemonaid-admin")
               .pathsToMatch("/v1/admin/**")
//               .pathsToMatch("/v1/credentials/**")
//               .pathsToMatch("/v1/tenants/**")
               .build();
   }

  @Bean
  public OpenAPI lemonaidOpenAPI() {
    return new OpenAPI()
      .info(new Info().title("Lemonaid Solution API")
        .description("Lemonaid DID Solution")
        .version("v0.0.1"));
  }
}
