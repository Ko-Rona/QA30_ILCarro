package models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
/*
@AllArgsConstructor
@NoArgsConstructor
*/
public class Car {

    String address;
    String make;
    String model;
    String year;
    String engine;
    String fuel;

    String gear;
    String wD;

    String doors;
    String seats;
    String carClass;

    String fuelConsumption;
    String carRegNumber;
    String price;
    String distanceIncluded;
    String typeFeature;
    String about;

}
