package co.hafid.speltutorial;

import co.hafid.speltutorial.properties.StudentProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpeltutorialApplicationTests {

    @Autowired
    private StudentProperties studentProperties;

    @Test
    void context_loads() {

    }

}
