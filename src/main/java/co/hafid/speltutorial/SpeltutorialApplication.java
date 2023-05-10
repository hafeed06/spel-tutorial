package co.hafid.speltutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableConfigurationProperties(NewDealerNetworkProperties.class)
public class SpeltutorialApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(SpeltutorialApplication.class, args);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
