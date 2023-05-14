package team6.car.vehicle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team6.car.apartment.domain.Apartment;
import team6.car.member.domain.Member;
import team6.car.vehicle.DTO.MainPageInfoDto;
import team6.car.vehicle.DTO.VehicleDto;
import team6.car.vehicle.domain.Vehicle;
import team6.car.vehicle.repository.VehicleRepository;
import team6.car.vehicle.response.StatusEnum;
import team6.car.vehicle.response.Message;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    /** 출차 시간 등록 **/
    @Override
    public ResponseEntity<Message> enrollDeparturetime(Long id, LocalDateTime exitTime, Boolean isLongTermParking){
        Vehicle vehicle= vehicleRepository.findById(id).orElseThrow(()->new RuntimeException("차량 정보를 찾을 수 없습니다."));
        VehicleDto vehicleDto=VehicleDto.builder()
                .exitTime(exitTime)
                .isLongTermParking(isLongTermParking)
                .build();
        vehicle.setVehicle_departuretime(vehicleDto.getExitTime());

        String message;
        StatusEnum status;

        if (vehicle.getVehicle_departuretime() != null) {
            message = "출차 시간 등록이 완료되었습니다.";
            status = StatusEnum.OK;
        } else {
            message = "출차 시간 등록에 실패하였습니다.";
            status = StatusEnum.INTERNAL_SERVER_ERROR;
        }

        // 응답 생성
        Message responseMessage = new Message();
        responseMessage.setStatus(status);
        responseMessage.setMessage(message);
        responseMessage.setData(null); // 데이터 필요 시 해당 필드에 데이터 객체를 설정

        return ResponseEntity.status(status.getStatusCode()).body(responseMessage);
    }

    /** 출차 시간 수정 **/
    @Override
    public ResponseEntity<Message> modifyDeparturetime(Long id, LocalDateTime exitTime, Boolean isLongTermParking){
        Vehicle vehicle=vehicleRepository.findById(id).orElseThrow(()->new RuntimeException("차량 정보를 찾을 수 없습니다."));
        vehicle.setVehicle_departuretime(exitTime);
        vehicle.setNo_departure(isLongTermParking);
        String message;
        StatusEnum status;

        if (vehicle.getVehicle_departuretime() != null) {
            message = "출차 시간 수정이 완료되었습니다.";
            status = StatusEnum.OK;
        } else {
            message = "출차 시간 수정에 실패하였습니다.";
            status = StatusEnum.INTERNAL_SERVER_ERROR;
        }

        // 응답 생성
        Message responseMessage = new Message();
        responseMessage.setStatus(status);
        responseMessage.setMessage(message);
        responseMessage.setData(null); // 데이터 필요 시 해당 필드에 데이터 객체를 설정

        return ResponseEntity.status(status.getStatusCode()).body(responseMessage);
    }

    /** 출차 시간 조회 (메인페이지)**/
    @Override
    public MainPageInfoDto getMainPageInfo(Long id){
        Vehicle vehicle = vehicleRepository.findByIdWithMember(id)
                .orElseThrow(() -> new RuntimeException("차량 정보를 찾을 수 없습니다."));
        Member member = vehicle.getMember();
        String address = member.getAddress();
        String apartment =member.getApartment().getApartment_name();

        LocalDateTime exitTime=vehicle.getVehicle_departuretime();
        LocalDateTime currentTime=LocalDateTime.now();
        Duration remainintTime=Duration.between(currentTime,exitTime);

        return MainPageInfoDto.builder()
                .exitTime(exitTime)
                .remainingTime(remainintTime)
                .isLongTermParking(vehicle.getNo_departure())
                .address(address)
                .apartment(apartment)
                .build();
    }
}
