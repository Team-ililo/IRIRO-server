package team6.car.vehicle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team6.car.vehicle.DTO.NearVehicleDto;
import team6.car.vehicle.DTO.VehicleDto;
import team6.car.vehicle.domain.Vehicle;
import team6.car.vehicle.response.Message;
import team6.car.vehicle.response.StatusEnum;
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
    public ResponseEntity<Message> enrollDeparturetime(@PathVariable Long id, @RequestBody VehicleDto vehicleDto){
        ResponseEntity<Message> response;

        try {
            // 출차 정보 등록
            vehicleService.enrollDeparturetime(id, vehicleDto.getExitTime(), vehicleDto.isLongTermParking());

            // 출차 정보 등록 성공 응답 생성
            String message = "출차 시간 등록이 완료되었습니다.";
            StatusEnum status = StatusEnum.OK;
            Message responseMessage = new Message();
            responseMessage.setStatus(status);
            responseMessage.setMessage(message);
            responseMessage.setData(null);
            response = ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            // 출차 정보 등록 실패 응답 생성
            String message = "출차 시간 등록에 실패하였습니다.";
            StatusEnum status = StatusEnum.INTERNAL_SERVER_ERROR;
            Message responseMessage = new Message();
            responseMessage.setStatus(status);
            responseMessage.setMessage(message);
            responseMessage.setData(null);
            response = ResponseEntity.status(status.getStatusCode()).body(responseMessage);
        }

        return response;
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
    public ResponseEntity<List<NearVehicleDto>> getNearVehicles(@PathVariable("device_id") Long deviceId) {
        List<NearVehicleDto> nearVehicles = nearVehicleService.getNearVehicle(deviceId);
        return ResponseEntity.ok(nearVehicles);
    }
}
