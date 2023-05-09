package team6.car.vehicle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team6.car.member.domain.Member;
import team6.car.vehicle.DTO.VehicleDto;
import team6.car.vehicle.domain.Vehicle;
import team6.car.vehicle.repository.VehicleRepository;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    /** 출차 시간 등록 **/
    @Override
    public Vehicle enrollDeparturetime(Long id, LocalDateTime exitTime, Boolean isLongTermParking){
        Vehicle vehicle= vehicleRepository.findById(id).orElseThrow(()->new RuntimeException("차량 정보를 찾을 수 없습니다."));
        VehicleDto vehicleDto=VehicleDto.builder()
                .exitTime(exitTime)
                .isLongTermParking(isLongTermParking)
                .build();
        vehicle.setVehicle_departuretime(vehicleDto.getExitTime());
        return vehicle;
    }

    /** 출차 시간 수정 **/
    @Override
    public Vehicle modifyDeparturetime(Long id, LocalDateTime exitTime, Boolean isLongTermParking){
        Vehicle vehicle=vehicleRepository.findById(id).orElseThrow(()->new RuntimeException("차량 정보를 찾을 수 없습니다."));
        vehicle.setVehicle_departuretime(exitTime);
        vehicle.setNo_departure(isLongTermParking);
        return vehicle;
    }

    /** 출차 시간 조회 **/
    @Override
    public VehicleDto getDeparturetime(Long id){
        Vehicle vehicle = vehicleRepository.findByIdWithMember(id)
                .orElseThrow(() -> new RuntimeException("차량 정보를 찾을 수 없습니다."));
        Member member = vehicle.getMember();
        String address = member.getAddress();
        return VehicleDto.builder()
                .id(vehicle.getVehicle_id())
                .vehicle_number(vehicle.getVehicle_number())
                .model(vehicle.getVehicle_model())
                .color(vehicle.getVehicle_color())
                .exitTime(vehicle.getVehicle_departuretime())
                .isLongTermParking(vehicle.getNo_departure())
                .address(address)
                .build();
    }
}
