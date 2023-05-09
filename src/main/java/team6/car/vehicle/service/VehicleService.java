package team6.car.vehicle.service;

import team6.car.vehicle.DTO.VehicleDto;
import team6.car.vehicle.domain.Vehicle;

import java.time.LocalDateTime;

public interface VehicleService {

    Vehicle enrollDeparturetime(Long id, LocalDateTime exitTime, Boolean isLongTermParking);
    Vehicle modifyDeparturetime(Long id, LocalDateTime exitTime, Boolean isLongTermParking);
    VehicleDto getDeparturetime(Long id);
}
