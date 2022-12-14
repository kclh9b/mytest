package study.mytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MytestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MytestApplication.class, args);
	}

}
