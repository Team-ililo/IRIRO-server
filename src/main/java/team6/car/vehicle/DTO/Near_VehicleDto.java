package team6.car.vehicle.DTO;

import lombok.*;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Near_VehicleDto {
    private Long id;
    private String vehicle_number;
    private String model;
    private String color;
    private LocalDateTime exitTime;
    private boolean isLongTermParking;
    private boolean isSatisfied;
}
