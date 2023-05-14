package team6.car.vehicle.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class NearVehicleDto {
    private String vehicle_number;
    private String model;
    private String color;
    private LocalTime exitTime;
    private boolean isLongTermParking;
    private boolean isSatisfied;
}
