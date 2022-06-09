package did;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //.. add
@SpringBootApplication
public class Application implements CommandLineRunner  {
	private static Logger LOG = LoggerFactory.getLogger(Application.class);



//	@LoadBalanced
//	@Bean
//	RestTemplate restTemplate(){
//		RestTemplate template = new  RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		List accept = new ArrayList();
//		accept.add(MediaType.APPLICATION_JSON);
//		headers.setAccept(accept);
//
//		return template;
//	}
	


	public static void main(String[] args) {
		//..SpringApplication app = new SpringApplication(Application.class);
		//..app.setBannerMode(Banner.Mode.OFF);
		//..app.run(args);
        SpringApplication.run(Application.class, args);
	}

    @Value("${logging.level.did}")
    private String logLevel;

    @Value("${cors.patterns}")
    private String corsPatterns;

    @Override
    public void run(String... args) throws Exception {
        LOG.debug("[config] logging.level.did={}", logLevel);
        LOG.debug("[config] cors.patterns={}", corsPatterns);
    }

//  @Bean
//  public Docket productApi() {
//    return new Docket(DocumentationType.SWAGGER_2).select()
//      .apis(RequestHandlerSelectors.basePackage("did")).build();
//  }
}
