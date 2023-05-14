package team6.car.vehicle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team6.car.device.domain.Device;
import team6.car.vehicle.domain.Vehicle;
import team6.car.device.domain.NearDevice;
import team6.car.device.repository.DeviceRepository;
import team6.car.device.repository.NearDeviceRepository;
import team6.car.vehicle.DTO.NearVehicleDto;
import team6.car.vehicle.domain.NearVehicle;
import team6.car.vehicle.repository.NearVehicleRepository;
import team6.car.vehicle.repository.VehicleRepository;
import team6.car.vehicle.response.Message;
import team6.car.vehicle.response.StatusEnum;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NearVehicleServiceImpl implements NearVehicleService {
    private final NearVehicleRepository nearVehicleRepository;
    private final VehicleRepository vehicleRepository;
    private final DeviceRepository deviceRepository;
    private final NearDeviceRepository nearDeviceRepository;

    /** 필요하면 Near_Vehicle에 정보 저장하는 기능 추가**/

    /**
     * 주변 차량 정보 조회
     **/

    public List<NearVehicleDto> getNearVehicle(Long device_id) {
        Device device = deviceRepository.findById(device_id)
                .orElseThrow(() -> new EntityNotFoundException("Device not found"));

        List<NearVehicle> nearVehicles = nearVehicleRepository.findByDeviceId(device_id)
                .orElseThrow(() -> new EntityNotFoundException("Near Vehicles not found"));

        LocalTime myDepartureTime = vehicleRepository.findByDeviceId(device_id)
                .map(Vehicle::getVehicle_departuretime)
                .orElse(null); // null로 초기화

        return nearVehicles.stream()
                .map(nearVehicle -> {
                    LocalTime nearVehicleDepartureTime = nearVehicle.getNear_vehicle_departuretime();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    String formattedExitTime = nearVehicleDepartureTime != null ? nearVehicleDepartureTime.format(formatter) : null;

                    boolean isSatisfied;
                    if (myDepartureTime == null || nearVehicleDepartureTime == null) {
                        isSatisfied = true;
                    } else {
                        isSatisfied = myDepartureTime.isBefore(nearVehicleDepartureTime);
                    }

                    // isLongTermParking이 true이면 satisfied를 true로 설정
                    if (nearVehicle.getNo_departure()) {
                        isSatisfied = true;
                    }

                    NearVehicleDto.NearVehicleDtoBuilder builder = NearVehicleDto.builder()
                            .vehicle_number(nearVehicle.getNear_vehicle_number())
                            .model(nearVehicle.getNear_vehicle_model())
                            .color(nearVehicle.getNear_vehicle_color())
                            .exitTime(formattedExitTime)
                            .isLongTermParking(nearVehicle.getNo_departure())
                            .isSatisfied(isSatisfied);

                    return builder.build();
                })
                .collect(Collectors.toList());
    }
}