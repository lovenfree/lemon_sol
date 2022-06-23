package did.lemonaid.solution.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig
{
  @Bean
   public GroupedOpenApi publicApi() {
       return GroupedOpenApi.builder()
               .group("lemonaid- Trust Registry")
               .pathsToMatch("/api/v1/trust-registry/**")
               .build();
   }

   @Bean
   public GroupedOpenApi adminApi() {
       return GroupedOpenApi.builder()
               .group("lemonaid-admin")
               .pathsToMatch("/api/v1/accounts/**")
               .pathsToMatch("/api/v1/credentials/**")
               .pathsToMatch("/api/v1/tenants/**")
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
