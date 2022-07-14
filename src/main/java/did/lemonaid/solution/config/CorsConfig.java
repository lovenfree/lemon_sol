package did.lemonaid.solution.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class CorsConfig implements WebMvcConfigurer {

  @Value("${cors.patterns}")
  private String corsPatterns;

  @Override
  public void addCorsMappings(CorsRegistry registry){
    registry.addMapping("/**")
      .allowedOriginPatterns(corsPatterns)
      .allowedMethods("GET","POST","HEAD","PUT","DELETE","OPTIONS","PATCH")
      .allowedHeaders("Content-Type","X-Requested-With","accept","Origin",
        "Access-Control-Request-Method","submissionid","Cache-Control")
      .exposedHeaders("Access-Control-Allow-Headers", "Access-Control-Origin",
        "Access-Control-Allow-Credentials")
      .allowCredentials(true).maxAge(3600L);
  }
}
