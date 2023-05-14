package team6.car.vehicle.service;

import org.springframework.http.ResponseEntity;
import team6.car.vehicle.DTO.MainPageInfoDto;
import team6.car.vehicle.DTO.VehicleDto;
import team6.car.vehicle.domain.Vehicle;
import team6.car.vehicle.response.Message;

import java.time.LocalDateTime;

public interface VehicleService {

    ResponseEntity<Message> enrollDeparturetime(Long id, LocalDateTime exitTime, Boolean isLongTermParking);
    ResponseEntity<Message> modifyDeparturetime(Long id, LocalDateTime exitTime, Boolean isLongTermParking);
    MainPageInfoDto getMainPageInfo(Long id);
}
