package co.hafid.speltutorial.properties;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author abelb
 */

@Component
@ConfigurationProperties(prefix = "app.properties")
@Getter
@Setter
public class StudentProperties {

    private List<Student> students;

    @Getter
    @Setter
    public static class Student {
        private String firstName;
        private String lastNAme;
    }

}
