package did.lemonaid.solution.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    String[] adminPaths = {"/v1/admin/**", "/v1/auth/**"};
       return GroupedOpenApi.builder()
               .group("lemonaid-admin")
               .pathsToMatch(adminPaths)
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
