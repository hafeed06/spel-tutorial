package co.hafid.speltutorial;

import co.hafid.speltutorial.properties.NewDealerNetworkProperties;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(NewDealerNetworkProperties.class)
public class SpeltutorialApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(SpeltutorialApplication.class, args);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Bean
	ApplicationRunner applicationRunner(NewDealerNetworkProperties testProperties) {
		return args -> {
			System.out.println(testProperties);
		};
	}

}
