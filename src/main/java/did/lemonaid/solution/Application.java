package did.lemonaid.solution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Slf4j
//@EnableRedisHttpSession
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Value("${logging.level.did}")
    private String logLevel;

    @Value("${cors.patterns}")
    private String corsPatterns;

    @Override
    public void run(String... args) throws Exception {
        log.debug("[config] logging.level.did={}", logLevel);
        log.debug("[config] cors.patterns={}", corsPatterns);
    }
}
