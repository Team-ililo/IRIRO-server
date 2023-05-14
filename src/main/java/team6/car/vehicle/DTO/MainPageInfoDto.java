package team6.car.vehicle.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MainPageInfoDto {

    private LocalDateTime exitTime;
    private Duration remainingTime;
    private boolean isLongTermParking;
    private String address;
    private String apartment;
    public MainPageInfoDto() {
        // 기본 생성자
    }
    public MainPageInfoDto(LocalDateTime exitTime,Duration remainingTime, boolean isLongTermParking, String address, String apartment) {
        this.exitTime = exitTime;
        this.remainingTime=remainingTime;
        this.isLongTermParking = isLongTermParking;
        this.address = address;
        this.apartment=apartment;
    }

}