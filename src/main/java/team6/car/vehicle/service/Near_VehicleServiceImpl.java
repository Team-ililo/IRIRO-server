package team6.car.vehicle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team6.car.device.repository.DeviceRepository;
import team6.car.vehicle.DTO.Near_VehicleDto;
import team6.car.vehicle.domain.Near_Vehicle;
import team6.car.vehicle.repository.Near_VehicleRepository;
import team6.car.vehicle.repository.VehicleRepository;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class Near_VehicleServiceImpl implements Near_VehicleService {
    private final Near_VehicleRepository near_VehicleRepository;

    /** 출차 시간 등록 **/
    @Override
    public Near_Vehicle enrollDeparturetime(Long id, LocalDateTime exitTime, Boolean isLongTermParking){
        Near_Vehicle near_vehicle=near_VehicleRepository.findById(id).orElseThrow(()->new RuntimeException("차량 정보를 찾을 수 없습니다."));
        Near_VehicleDto near_vehicleDto=Near_VehicleDto.builder()
                                        .exitTime(exitTime)
                                        .isLongTermParking(isLongTermParking)
                                        .build();
        near_vehicle.setNear_vehicle_departuretime(near_vehicleDto.getExitTime());
        return near_VehicleRepository.save(near_vehicle);
    }

    /** 출차 시간 수정 **/
    @Override
    public Near_Vehicle modifyDeparturetime(Long id, LocalDateTime exitTime, Boolean isLongTermParking){
        Near_Vehicle near_vehicle=near_VehicleRepository.findById(id).orElseThrow(()->new RuntimeException("차량 정보를 찾을 수 없습니다."));
        near_vehicle.setNear_vehicle_departuretime(exitTime);
        near_vehicle.setNo_departure(isLongTermParking);
        return near_vehicle;
    }

    /** 출차 시간 조회 **/
    @Override
    public Near_Vehicle getDeparturetime(Long id){
        Near_Vehicle near_vehicle=near_VehicleRepository.findById(id).orElseThrow(()->new RuntimeException("차량 정보를 찾을 수 없습니다."));
        return near_vehicle;
    }
}
