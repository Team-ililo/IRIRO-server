package team6.car.vehicle.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team6.car.vehicle.DTO.MainPageDto;
import team6.car.vehicle.DTO.NearVehicleDto;
import team6.car.vehicle.DTO.VehicleDto;
import team6.car.vehicle.response.Message;
import team6.car.vehicle.response.StatusEnum;
import team6.car.vehicle.service.NearVehicleService;
import team6.car.vehicle.service.VehicleService;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequiredArgsConstructor
public class VehicleController {
    @Autowired
    private final VehicleService vehicleService;
    @Autowired
    private final NearVehicleService nearVehicleService;


    /**
     * 출차 정보 등록
     **/
    @ApiOperation(value="출차 정보 등록", notes="출차 시간 및 장기 주차 여부 등록")
    @ApiImplicitParam(
            name = "id",
            value = "차량 id",
            required = true,
            dataType = "long",
            paramType = "path"
    )
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 400, message="BAD_REQUEST"),
            @ApiResponse(code = 404, message="NOT_FOUND"),
            @ApiResponse(code = 500, message="INTERNAL_SERVER_ERROR")
    })
    @PostMapping("/vehicle/departuretime/{id}")
    public ResponseEntity<Message> enrollDeparturetime(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
        ResponseEntity<Message> response;


        try {
            // 출차 정보 등록
            vehicleService.enrollDeparturetime(id, vehicleDto.getExitTime(), vehicleDto.getNo_departure());

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
    @ApiOperation(value="출차 정보 수정", notes="출차 시간 및 장기 주차 여부 수정")
    @ApiImplicitParam(
            name = "id",
            value = "차량 id",
            required = true,
            dataType = "long",
            paramType = "path"
    )
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 400, message="BAD_REQUEST"),
            @ApiResponse(code = 404, message="NOT_FOUND"),
            @ApiResponse(code = 500, message="INTERNAL_SERVER_ERROR")
    })
    @PutMapping("/vehicle/departuretime/{id}")
    public ResponseEntity<Message> modifyDeparturetime(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
        ResponseEntity<Message> response;

        try {
            // 출차 정보 수정
            vehicleService.modifyDeparturetime(id, vehicleDto.getExitTime(), vehicleDto.getNo_departure());

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

    /**
     * 출차 정보 조회(메인페이지)
     **/
    @ApiOperation(value="출차 정보 및 주소 조회", notes="메인페이지에 출력될 출차 시간, 남은 시간, 주소 정보 조회")
    @ApiImplicitParam(
            name = "id",
            value = "차량 id",
            required = true,
            dataType = "long",
            paramType = "path"
    )
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 400, message="BAD_REQUEST"),
            @ApiResponse(code = 404, message="NOT_FOUND"),
            @ApiResponse(code = 500, message="INTERNAL_SERVER_ERROR")
    })
    @GetMapping("/vehicle/departuretime/{id}")
    public ResponseEntity<Message> getMainPageInfo(@PathVariable Long id) {
        Message responseMessage;
        HttpStatus httpStatus;

        try {
            // 출차 정보 조회
            MainPageDto mainPageDto = vehicleService.getDeparturetime(id);

            if (mainPageDto!=null) {
            // 출차 정보 존재
                String message = "출차 정보 조회가 완료되었습니다.";
                StatusEnum status = StatusEnum.OK;
                responseMessage = new Message();
                responseMessage.setStatus(status);
                responseMessage.setMessage(message);
                responseMessage.setData(mainPageDto);

                httpStatus = HttpStatus.OK;
            } else {

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

    /**
     * 주변 차량 정보 조회
     **/
    @ApiOperation(value="주변 차량 정보 조회", notes="주변 차량의 정보 및 출차 시간 조회")
    @ApiImplicitParam(
            name = "id",
            value = "디바이스 id",
            required = true,
            dataType = "long",
            paramType = "path"
    )
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 400, message="BAD_REQUEST"),
            @ApiResponse(code = 404, message="NOT_FOUND(주변 차량이 없습니다. 디바이스를 찾을 수 없습니다.)"),
            @ApiResponse(code = 500, message="INTERNAL_SERVER_ERROR(내부 서버 오류)")
    })
    @GetMapping("/nearvehicles/{id}")
    public ResponseEntity<Message<List<NearVehicleDto>>> getNearVehicles(@PathVariable("id") Long deviceId) {
        try {
            List<NearVehicleDto> nearVehicles = nearVehicleService.getNearVehicle(deviceId);

            if (!nearVehicles.isEmpty()) {
                Message<List<NearVehicleDto>> response = new Message<>();
                response.setStatus(StatusEnum.OK);
                response.setMessage("성공");
                response.setData(nearVehicles);
                return ResponseEntity.ok(response);
            } else {
                Message<List<NearVehicleDto>> response = new Message<>();
                response.setStatus(StatusEnum.NOT_FOUND);
                response.setMessage("주변 차량이 없습니다.");
                response.setData(Collections.emptyList());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (EntityNotFoundException e) {
            Message<List<NearVehicleDto>> response = new Message<>();
            response.setStatus(StatusEnum.NOT_FOUND);
            response.setMessage("디바이스를 찾을 수 없습니다.");
            response.setData(Collections.emptyList());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (RuntimeException e) {
            Message<List<NearVehicleDto>> response = new Message<>();
            response.setStatus(StatusEnum.INTERNAL_SERVER_ERROR);
            response.setMessage("내부 서버 오류");
            response.setData(Collections.emptyList());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}