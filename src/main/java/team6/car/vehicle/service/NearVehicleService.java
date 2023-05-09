package team6.car.vehicle.service;

import team6.car.vehicle.DTO.NearVehicleDto;

import java.util.List;

public interface NearVehicleService {
    List<NearVehicleDto> getNearVehicle(Long device_id);
}
