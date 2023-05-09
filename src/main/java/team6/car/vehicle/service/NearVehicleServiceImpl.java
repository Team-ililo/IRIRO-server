package team6.car.vehicle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team6.car.device.domain.Device;
import team6.car.device.domain.Near_Device;
import team6.car.device.repository.DeviceRepository;
import team6.car.device.repository.NearDeviceRepository;
import team6.car.vehicle.DTO.NearVehicleDto;
import team6.car.vehicle.domain.NearVehicle;
import team6.car.vehicle.repository.NearVehicleRepository;
import team6.car.vehicle.repository.VehicleRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NearVehicleServiceImpl implements NearVehicleService {
    private final NearVehicleRepository near_VehicleRepository;
    private final VehicleRepository vehicleRepository;
    private final DeviceRepository deviceRepository;
    private final NearDeviceRepository nearDeviceRepository;

    /** 필요하면 Near_Vehicle에 정보 저장하는 기능 추가**/

    /** 주변 차량 정보 조회 **/
    public List<NearVehicleDto> getNearVehicle(Long device_id) {
        Device device = deviceRepository.findById(device_id)
                .orElseThrow(() -> new EntityNotFoundException("Device not found"));
        Near_Device nearDevice = nearDeviceRepository.findByDevice(device_id)
                .orElseThrow(() -> new EntityNotFoundException("Near Device not found"));

        LocalDateTime myDepartureTime = vehicleRepository.findById(device_id).orElseThrow(() -> new EntityNotFoundException("Vehicle not found")).getVehicle_departuretime();

        List<NearVehicle> nearVehicles = near_VehicleRepository.findByNearDeviceDeviceIdAndNoDepartureIsFalse(device_id);

        return nearVehicles.stream()
                .map(nearVehicle -> NearVehicleDto.builder()
                        .id(nearVehicle.getNear_vehicle_id())
                        .vehicle_number(nearVehicle.getNear_vehicle_number())
                        .model(nearVehicle.getNear_vehicle_model())
                        .color(nearVehicle.getNear_vehicle_color())
                        .exitTime(nearVehicle.getNear_vehicle_departuretime())
                        .isLongTermParking(nearVehicle.getNo_departure())
                        .isSatisfied(myDepartureTime.isBefore(nearVehicle.getNear_vehicle_departuretime()))
                        .build())
                .collect(Collectors.toList());
    }

}
