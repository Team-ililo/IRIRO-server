package team6.car.vehicle.service;

import org.springframework.http.ResponseEntity;
import team6.car.vehicle.DTO.MainPageDto;
import team6.car.vehicle.DTO.VehicleDto;
import team6.car.vehicle.response.Message;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface VehicleService {

    ResponseEntity<Message> enrollDeparturetime(Long id, LocalTime exitTime, boolean isLongTermParking);
    ResponseEntity<Message> modifyDeparturetime(Long id, LocalTime exitTime, boolean isLongTermParking);
    MainPageDto getDeparturetime(Long id);
}
