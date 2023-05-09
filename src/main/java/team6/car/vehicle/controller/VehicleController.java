package team6.car.vehicle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team6.car.vehicle.DTO.NearVehicleDto;
import team6.car.vehicle.DTO.VehicleDto;
import team6.car.vehicle.domain.Vehicle;
import team6.car.vehicle.service.NearVehicleService;
import team6.car.vehicle.service.VehicleService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class VehicleController {
    @Autowired
    private final VehicleService vehicleService;
    @Autowired
    private final NearVehicleService nearVehicleService;


    /** 출차 정보 등록 **/
    @PostMapping("/vehicle/departuretime/{id}")
    public ResponseEntity<Vehicle> enrollDeparturetime(@PathVariable Long id, @RequestBody VehicleDto vehicleDto){
        Vehicle vehicle = vehicleService.enrollDeparturetime(id,vehicleDto.getExitTime(),vehicleDto.isLongTermParking());
        return ResponseEntity.ok(vehicle);
    }

    /** 출차 정보 수정 **/
    @PutMapping("/vehicle/departuretime/{id}")
    public ResponseEntity<Vehicle> modifyDeparturetime(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleService.modifyDeparturetime(id, vehicleDto.getExitTime(), vehicleDto.isLongTermParking());
        return ResponseEntity.ok(vehicle);
    }

    /** 출차 정보 조회 **/
    @GetMapping("/vehicleDto/departuretime/{id}")
    public ResponseEntity<VehicleDto> getDeparturetime(@PathVariable Long id) {
        VehicleDto vehicleDto = vehicleService.getDeparturetime(id);
        return ResponseEntity.ok(vehicleDto);
    }

    /** 주변 차량 정보 조회 **/
    @GetMapping("/nearvehicles/{device_id}")
    public ResponseEntity<List<NearVehicleDto>> getNearVehicles(@PathVariable Long device_id) {
        List<NearVehicleDto> nearVehicles = nearVehicleService.getNearVehicle(device_id);
        return ResponseEntity.ok(nearVehicles);
    }
}
