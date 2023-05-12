package team6.car.vehicle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
public class VehicleController {
    @Autowired
    private final VehicleService vehicleService;
    @Autowired
    private final NearVehicleService nearVehicleService;


    /**
     * 출차 정보 등록
     **/
    @PostMapping("/vehicle/departuretime/{id}")
    public ResponseEntity<Message> enrollDeparturetime(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
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
        } catch (IllegalArgumentException e) {
            // 잘못된 요청인 경우 (BAD_REQUEST)
            String message = "잘못된 요청입니다.";
            StatusEnum status = StatusEnum.BAD_REQUEST;
            Message responseMessage = new Message();
            responseMessage.setStatus(status);
            responseMessage.setMessage(message);
            responseMessage.setData(null);
            response = ResponseEntity.status(status.getStatusCode()).body(responseMessage);
        } catch (Exception e) {
            // 내부 서버 오류인 경우 (INTERNAL_SERVER_ERROR)
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

    /**
     * 출차 정보 수정
     **/
    @PutMapping("/vehicle/departuretime/{id}")
    public ResponseEntity<Message> modifyDeparturetime(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
        ResponseEntity<Message> response;

        try {
            // 출차 정보 수정
            vehicleService.modifyDeparturetime(id, vehicleDto.getExitTime(), vehicleDto.isLongTermParking());

            // 출차 정보 수정 성공 응답 생성
            String message = "출차 시간 수정이 완료되었습니다.";
            StatusEnum status = StatusEnum.OK;
            Message responseMessage = new Message();
            responseMessage.setStatus(status);
            responseMessage.setMessage(message);
            responseMessage.setData(null);
            response = ResponseEntity.ok(responseMessage);
        } catch (IllegalArgumentException e) {
            // 잘못된 요청인 경우 (BAD_REQUEST)
            String message = "잘못된 요청입니다.";
            StatusEnum status = StatusEnum.BAD_REQUEST;
            Message responseMessage = new Message();
            responseMessage.setStatus(status);
            responseMessage.setMessage(message);
            responseMessage.setData(null);
            response = ResponseEntity.status(status.getStatusCode()).body(responseMessage);
        } catch (NoSuchElementException e) {
            // 차량 정보를 찾을 수 없는 경우 (NOT_FOUND)
            String message = "차량 정보를 찾을 수 없습니다.";
            StatusEnum status = StatusEnum.NOT_FOUND;
            Message responseMessage = new Message();
            responseMessage.setStatus(status);
            responseMessage.setMessage(message);
            responseMessage.setData(null);
            response = ResponseEntity.status(status.getStatusCode()).body(responseMessage);
        } catch (Exception e) {
            // 내부 서버 오류인 경우 (INTERNAL_SERVER_ERROR)
            String message = "내부 서버 오류가 발생하였습니다.";
            StatusEnum status = StatusEnum.INTERNAL_SERVER_ERROR;
            Message responseMessage = new Message();
            responseMessage.setStatus(status);
            responseMessage.setMessage(message);
            responseMessage.setData(null);
            response = ResponseEntity.status(status.getStatusCode()).body(responseMessage);
        }

        return response;
    }

    /** 출차 정보 조회 **/
    @GetMapping("/vehicleDto/departuretime/{id}")
    public ResponseEntity<Message> getDeparturetime(@PathVariable Long id) {
        Message responseMessage;
        HttpStatus httpStatus;

        try {
            // 출차 정보 조회
            VehicleDto vehicleDto = vehicleService.getDeparturetime(id);

            if (vehicleDto != null) {
                // 출차 정보가 있는 경우 성공 응답 생성
                String message = "출차 정보 조회가 완료되었습니다.";
                StatusEnum status = StatusEnum.OK;
                responseMessage = new Message();
                responseMessage.setStatus(status);
                responseMessage.setMessage(message);
                responseMessage.setData(vehicleDto);
                httpStatus = HttpStatus.OK;
            } else {
                // 출차 정보가 없는 경우 NotFound 응답 생성
                String message = "출차 정보가 존재하지 않습니다.";
                StatusEnum status = StatusEnum.NOT_FOUND;
                responseMessage = new Message();
                responseMessage.setStatus(status);
                responseMessage.setMessage(message);
                responseMessage.setData(null);
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            // 출차 정보 조회 실패 응답 생성
            String message = "출차 정보 조회에 실패하였습니다.";
            StatusEnum status = StatusEnum.INTERNAL_SERVER_ERROR;
            responseMessage = new Message();
            responseMessage.setStatus(status);
            responseMessage.setMessage(message);
            responseMessage.setData(null);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(httpStatus).body(responseMessage);
    }

    /** 주변 차량 정보 조회 **/
    @GetMapping("/nearvehicles/{device_id}")
    public ResponseEntity<List<NearVehicleDto>> getNearVehicles(@PathVariable("device_id") Long deviceId) {
        List<NearVehicleDto> nearVehicles = nearVehicleService.getNearVehicle(deviceId);
        return ResponseEntity.ok(nearVehicles);
    }
}
