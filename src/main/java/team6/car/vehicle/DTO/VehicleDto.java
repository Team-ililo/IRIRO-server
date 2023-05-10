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

    public VehicleDto() {
        // 기본 생성자
    }
    public VehicleDto(Long id, String vehicle_number, String model, String color, LocalDateTime exitTime, boolean isLongTermParking, String address) {
        this.id = id;
        this.vehicle_number = vehicle_number;
        this.model = model;
        this.color = color;
        this.exitTime = exitTime;
        this.isLongTermParking = isLongTermParking;
        this.address = address;
    }

}