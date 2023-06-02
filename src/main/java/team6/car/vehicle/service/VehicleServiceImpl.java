package team6.car.vehicle.service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
    private final VehicleRepository vehicleRepository;

    /**
     * 출차 시간 등록
     **/
    @Override
    public ResponseEntity<Message> enrollDeparturetime(Long id, LocalTime exitTime, Boolean isLongTermParking) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("차량 정보를 찾을 수 없습니다."));
        VehicleDto vehicleDto = VehicleDto.builder()
                .exitTime(exitTime)
                .no_departure(isLongTermParking)
                .build();
        vehicle.setVehicle_departuretime(vehicleDto.getExitTime());
        vehicle.setNo_departure(isLongTermParking);

        String message;
        StatusEnum status;

        System.out.println("This is a log message");
        logger.info("exitTime: {}", exitTime);
        logger.info("isLongTermParking: {}", isLongTermParking);

        if (Boolean.TRUE.equals(isLongTermParking) && (exitTime != null)) {
            message = "출차 시간 등록에 실패하였습니다. 장기 주차를 선택하셨습니다.";
            status = StatusEnum.BAD_REQUEST;
            throw new IllegalArgumentException(message);
        } else if (Boolean.FALSE.equals(isLongTermParking) && (exitTime == null)) {
            message = "출차 시간 등록에 실패하였습니다. 출차 시간을 등록하세요";
            status = StatusEnum.BAD_REQUEST;
            throw new IllegalArgumentException(message);
        } else {
            message = "출차 시간 등록이 완료되었습니다.";
            status = StatusEnum.OK;
        }

        // 응답 생성
        Message responseMessage = new Message();
        responseMessage.setStatus(status);
        responseMessage.setMessage(message);
        responseMessage.setData(null); // 데이터 필요 시 해당 필드에 데이터 객체를 설정

        return ResponseEntity.status(status.getStatusCode()).body(responseMessage);
    }

    /**
     * 출차 시간 수정
     **/
    @Override
    public ResponseEntity<Message> modifyDeparturetime(Long id, LocalTime exitTime, Boolean isLongTermParking) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("차량 정보를 찾을 수 없습니다."));
        vehicle.setVehicle_departuretime(exitTime);
        vehicle.setNo_departure(isLongTermParking);
        String message;
        StatusEnum status;

        if (Boolean.TRUE.equals(isLongTermParking) && exitTime != null) {
            message = "출차 시간 수정에 실패하였습니다. 장기 주차를 선택하셨습니다.";
            status = StatusEnum.BAD_REQUEST;
            throw new IllegalArgumentException(message);
        } else if (Boolean.FALSE.equals(isLongTermParking) && (exitTime == null)) {
            message = "출차 시간 등록에 실패하였습니다. 출차 시간을 등록하세요";
            status = StatusEnum.BAD_REQUEST;
            throw new IllegalArgumentException(message);
        } else {
            message = "출차 시간 수정이 완료되었습니다.";
            status = StatusEnum.OK;
        }

        // 응답 생성
        Message responseMessage = new Message();
        responseMessage.setStatus(status);
        responseMessage.setMessage(message);
        responseMessage.setData(null); // 데이터 필요 시 해당 필드에 데이터 객체를 설정

        return ResponseEntity.status(status.getStatusCode()).body(responseMessage);
    }

    /**
     * 출차 시간 조회
     **/
    @Override
    public MainPageDto getDeparturetime(Long id) {
        Vehicle vehicle = vehicleRepository.findByIdWithMember(id)
                .orElseThrow(() -> new RuntimeException("차량 정보를 찾을 수 없습니다."));
        Member member = vehicle.getMember();

        Apartment apartment = null;
        String apartmentName = null;
        String address = null;
        String formattedExitTime = null;
        long remainingMinutes = 0;
        long remainingMinutes1 = 0;
        long remainingMinutes2 = 0;
        LocalTime remainingTime = null;
        Boolean isLongTermParking = null;

        if (member != null) {
            apartment = member.getApartment();
            address = member.getAddress();
            if (apartment != null) {
                apartmentName = apartment.getApartment_name();
            }
        }

        LocalTime exitTime = vehicle.getVehicle_departuretime();
        LocalTime currentTime = LocalTime.now();

        if (exitTime != null && Boolean.FALSE.equals(vehicle.isNo_departure())) {
            formattedExitTime = exitTime.format(DateTimeFormatter.ofPattern("HH:mm"));


            if (currentTime.isBefore(exitTime)) {
                Duration duration = Duration.between(currentTime, exitTime);
                remainingMinutes = duration.toMinutes();

                if (remainingMinutes < 0) {
                    remainingMinutes += 24 * 60;
                }
            } else if (currentTime.isAfter(exitTime)) {
                Duration duration1 = Duration.between(currentTime, LocalTime.MIDNIGHT);
                remainingMinutes1 = duration1.toMinutes();
                if (remainingMinutes1 < 0) {
                    remainingMinutes1 += 24 * 60;
                }
                Duration duration2 = Duration.between(LocalTime.MIDNIGHT, exitTime);
                remainingMinutes2 = duration2.toMinutes();
                if (remainingMinutes2 < 0) {
                    remainingMinutes2 += 24 * 60;
                }
                remainingMinutes = remainingMinutes1 + remainingMinutes2;
            } else if(currentTime.equals(exitTime)){
                remainingMinutes=0;
            }
/*
            // 현재 시간 12:00:01~23:59:59
            if ((currentTime.isAfter(LocalTime.NOON) && currentTime.isBefore(LocalTime.of(23,59,59)))|| currentTime.equals(LocalTime.of(23,59,59))){

                Duration duration1=Duration.between(currentTime,LocalTime.MIDNIGHT);
                remainingMinutes1=duration1.toMinutes();
                if(remainingMinutes1<0){
                    remainingMinutes1+=24*60;
                }

                Duration duration2=Duration.between(LocalTime.MIDNIGHT,exitTime);
                remainingMinutes2=duration2.toMinutes();
                if(remainingMinutes2<0){
                    remainingMinutes2+=24*60;
                }

                remainingMinutes=remainingMinutes1+remainingMinutes2;
            }

            // 현재 시간 00:00:01 ~ 11:59:59
            else if(currentTime.isAfter(LocalTime.MIDNIGHT) && currentTime.isBefore(LocalTime.NOON)){

                Duration duration=Duration.between(currentTime,exitTime);
                remainingMinutes=duration.toMinutes();
                if(remainingMinutes<0){
                    remainingMinutes+=24*60;
                }

            }

            // 현재 시각 = 12:00:00
            else if(currentTime.equals(LocalTime.NOON)){
                Duration duration1=Duration.between(LocalTime.MIDNIGHT,exitTime);
                remainingMinutes1=duration1.toMinutes();
                if(remainingMinutes1<0){
                    remainingMinutes1+=24*60;
                }

                Duration duration2=Duration.between(LocalTime.NOON,LocalTime.MIDNIGHT);
                remainingMinutes2=duration2.toMinutes();
                if(remainingMinutes2<0){
                    remainingMinutes2+=24*60;
                }

                remainingMinutes=remainingMinutes1+remainingMinutes2;
            }

            // 현재 시작 = 24:00:00
            else if(currentTime.equals(LocalTime.MIDNIGHT)){
                Duration duration=Duration.between(LocalTime.MIDNIGHT,exitTime);
                remainingMinutes=duration.toMinutes();
            }
            if(remainingMinutes<0){
                remainingMinutes+=24*60;
            }
        }
*/
        }
            return MainPageDto.builder()
                    .exitTime(formattedExitTime)
                    .remainingTime(formatRemainingTime(remainingMinutes))
                    .isLongTermParking(vehicle.getNo_departure())
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
