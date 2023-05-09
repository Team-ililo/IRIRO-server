package team6.car.vehicle.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VehicleDto {
    private Long id;
    private String vehicle_number;
    private String model;
    private String color;
    private LocalDateTime exitTime;
    private boolean isLongTermParking;
    private String address;

}