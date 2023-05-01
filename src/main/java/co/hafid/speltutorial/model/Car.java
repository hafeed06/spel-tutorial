package co.hafid.speltutorial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author abelb
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {

    private String make;
    private String model;
    private Integer YearOfProduction;
    private Spec specs;

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", YearOfProduction=" + YearOfProduction +
                ", specs=" + specs +
                '}';
    }
}
