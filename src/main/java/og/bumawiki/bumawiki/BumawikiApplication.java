package og.bumawiki.bumawiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BumawikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BumawikiApplication.class, args);
	}

}
