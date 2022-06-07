package bcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Config.class)
public class AppMain {

	public static void main(String[] args) {
		SpringApplication.run(AppMain.class, args);
	}

}
