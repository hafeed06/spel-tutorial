package co.hafid.speltutorial.properties;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static co.hafid.speltutorial.properties.StudentProperties.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableConfigurationProperties(PropertiesIT.class)
class PropertiesIT {

    @Autowired
    private StudentProperties studentProperties;

    @Test
    void should_load_students_properties() {
        // Given - When
        List<Student> students = studentProperties.getStudents();
        // Then
        assertThat(students).hasSize(2);
        assertThat(students)
                .extracting(Student::getFirstName,
                            Student::getLastNAme)
                .contains(new Tuple("John", "Dist"),
                          new Tuple("French", "Montana"));
    }

}
