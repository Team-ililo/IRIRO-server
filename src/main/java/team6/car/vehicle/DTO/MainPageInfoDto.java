package team6.car.vehicle.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
public class MainPageInfoDto {

    private boolean isLongTermParking;
    private String address;
    private String apartment;
    public String formattedExitTime;
    private String formattedRemainingTime;
    public MainPageInfoDto() {
        // 기본 생성자
    }
    public MainPageInfoDto(boolean isLongTermParking, String address, String apartment, String formattedExitTime, String formattedRemainingTime) {
        this.isLongTermParking = isLongTermParking;
        this.address = address;
        this.apartment=apartment;
        this.formattedExitTime = formattedExitTime;
        this.formattedRemainingTime = formattedRemainingTime;
    }
}