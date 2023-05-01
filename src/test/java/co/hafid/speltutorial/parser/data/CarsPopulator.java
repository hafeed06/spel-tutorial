package co.hafid.speltutorial.parser.data;

import java.util.ArrayList;
import java.util.List;

import co.hafid.speltutorial.model.Car;
import co.hafid.speltutorial.model.DriveTrain;
import co.hafid.speltutorial.model.Spec;

/**
 * @author abelb
 */

public class CarsPopulator {

    public static List<Car> buildCarList() {
        List<Car> cars = new ArrayList<>();

        Car car1 = Car.builder()
                .make("Apollo")
                .model("IE")
                .YearOfProduction(2013)
                .specs(Spec.builder().
                        speed(10)
                        .braking(9)
                        .acceleration(8)
                        .handling(7)
                        .launch(6)
                        .driveTrain(
                                DriveTrain.builder()
                                        .rearWheelDrive(true)
                                        .turboEngine(true)
                                        .build()
                        )
                        .build())
                .build();
        Car car2 = Car.builder()
                .make("Maserati")
                .model("MC12")
                .YearOfProduction(2018)
                .specs(Spec.builder().
                        speed(9)
                        .braking(10)
                        .acceleration(9)
                        .handling(10)
                        .launch(8)
                        .driveTrain(
                                DriveTrain.builder()
                                        .rearWheelDrive(false)
                                        .turboEngine(true)
                                        .build()
                        )
                        .build())
                .build();

        Car car3 = Car.builder()
                .make("Apollo")
                .model("EV")
                .YearOfProduction(2020)
                .specs(Spec.builder().
                        speed(9)
                        .braking(10)
                        .acceleration(8)
                        .handling(10)
                        .launch(8)
                        .driveTrain(
                                DriveTrain.builder()
                                        .rearWheelDrive(false)
                                        .turboEngine(true)
                                        .build()
                        )
                        .build())
                .build();

        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        return cars;
    }
}
