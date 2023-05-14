package team6.car.vehicle.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
@Builder
public class MainPageDto {
    private String formattedExitTime;
    private String remainingTime;
    private boolean isLongTermParking;
    private String address;

}