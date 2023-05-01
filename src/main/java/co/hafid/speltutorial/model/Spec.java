package co.hafid.speltutorial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author abelb
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Spec {
    private Integer speed;
    private Integer handling;
    private Integer acceleration;
    private Integer launch;
    private Integer braking;

    private DriveTrain driveTrain;
}
