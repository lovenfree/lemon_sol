package did;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
  private static Logger LOG = LoggerFactory.getLogger(Application.class);

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
}
