package co.hafid.speltutorial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author abelb
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class DriveTrain {

    private Boolean rearWheelDrive;
    private Boolean turboEngine;

}
