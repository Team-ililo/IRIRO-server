package team6.car.vehicle.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class VehicleDto {
    private String vehicle_number;
    private String model;
    private String color;
    private LocalTime exitTime;
    private boolean isLongTermParking;
    private String address;

    public VehicleDto() {
        // 기본 생성자
    }
    public VehicleDto(String vehicle_number, String model, String color, LocalTime exitTime, boolean isLongTermParking, String address) {
        this.vehicle_number = vehicle_number;
        this.model = model;
        this.color = color;
        this.exitTime = exitTime;
        this.isLongTermParking = isLongTermParking;
        this.address = address;
    }

}