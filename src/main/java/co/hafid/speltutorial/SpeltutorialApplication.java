package co.hafid.speltutorial;

import co.hafid.speltutorial.properties.StudentProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties({StudentProperties.class})
public class SpeltutorialApplication {

	public static void main(String[] args) {
			SpringApplication.run(SpeltutorialApplication.class, args);   
	}

}
