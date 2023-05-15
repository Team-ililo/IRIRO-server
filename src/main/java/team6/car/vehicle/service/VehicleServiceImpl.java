package team6.car.vehicle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team6.car.apartment.domain.Apartment;
import team6.car.member.domain.Member;
import team6.car.vehicle.DTO.MainPageDto;
import team6.car.vehicle.DTO.VehicleDto;
import team6.car.vehicle.domain.Vehicle;
import team6.car.vehicle.repository.VehicleRepository;
import team6.car.vehicle.response.StatusEnum;
import team6.car.vehicle.response.Message;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    /** 출차 시간 등록 **/
    @Override
    public ResponseEntity<Message> enrollDeparturetime(Long id, LocalTime exitTime, Boolean isLongTermParking){
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
    public ResponseEntity<Message> modifyDeparturetime(Long id, LocalTime exitTime, Boolean isLongTermParking){
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

    /** 출차 시간 조회 **/
    @Override
    public MainPageDto getDeparturetime(Long id) {
        Vehicle vehicle = vehicleRepository.findByIdWithMember(id)
                .orElseThrow(() -> new RuntimeException("차량 정보를 찾을 수 없습니다."));
        Member member = vehicle.getMember();

        Apartment apartment = null;
        String apartmentName = null;

        if (member != null) {
            apartment = member.getApartment();
            if (apartment != null) {
                apartmentName = apartment.getApartment_name();
            }
        }

        String address = member.getAddress();
        LocalTime exitTime = vehicle.getVehicle_departuretime();
        String formattedExitTime;

        LocalTime currentTime = LocalTime.now();

        formattedExitTime = exitTime.format(DateTimeFormatter.ofPattern("HH:mm"));

        Duration duration = Duration.between(currentTime, exitTime);
        long remainingMinutes = duration.toMinutes();

        boolean isLongTermParking = vehicle.getNo_departure();

        if (remainingMinutes < 0) {
            // remainingTime이 음수인 경우: 24시간을 더해주어 현재 날 기준으로 처리
            duration = Duration.between(currentTime, exitTime);
            remainingMinutes = duration.toMinutes() + 24 * 60; // remainingTime에 24시간(1440분)을 더해줍니다.
        }

        return MainPageDto.builder()
                .formattedExitTime(formattedExitTime)
                .remainingTime(formatRemainingTime(remainingMinutes))
                .isLongTermParking(isLongTermParking)
                .apartmentName(apartmentName)
                .address(address)
                .build();
    }
    private String formatRemainingTime(long minutes) {
        long hours = minutes / 60;
        long remainingMinutes = minutes % 60;
        return String.format("%02d:%02d", hours, remainingMinutes);
    }
}
