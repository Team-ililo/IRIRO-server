package team6.car.vehicle.service;

import org.springframework.http.ResponseEntity;
import team6.car.vehicle.DTO.NearVehicleDto;
import team6.car.vehicle.response.Message;

import java.util.List;

public interface NearVehicleService {
    List<NearVehicleDto> getNearVehicle(Long device_id);
}
