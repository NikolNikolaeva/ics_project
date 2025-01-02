package ics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcsApplication.class, args);
	}

}
