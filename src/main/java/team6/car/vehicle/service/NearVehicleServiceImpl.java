package team6.car.vehicle.service;

import lombok.RequiredArgsConstructor;
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

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import team6.car.member.response.StatusEnum;
import team6.car.vehicle.response.Message;
@Service
@Transactional
@RequiredArgsConstructor
public class NearVehicleServiceImpl implements NearVehicleService {
    private final NearVehicleRepository nearVehicleRepository;
    private final VehicleRepository vehicleRepository;
    private final DeviceRepository deviceRepository;
    private final NearDeviceRepository nearDeviceRepository;

    /** 필요하면 Near_Vehicle에 정보 저장하는 기능 추가**/

    /** 주변 차량 정보 조회 **/

    public List<NearVehicleDto> getNearVehicle(Long device_id) {
        Device device = deviceRepository.findById(device_id)
                .orElseThrow(() -> new EntityNotFoundException("Device not found"));

        List<NearVehicle> nearVehicles = nearVehicleRepository.findByDeviceId(device_id)
                .orElseThrow(() -> new EntityNotFoundException("Near Vehicles not found"));

        LocalDateTime myDepartureTime = vehicleRepository.findByDeviceId(device_id)
                .map(Vehicle::getVehicle_departuretime)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        return nearVehicles.stream()
                .map(nearVehicle -> {
                    NearVehicleDto.NearVehicleDtoBuilder builder = NearVehicleDto.builder()
                            .id(nearVehicle.getNear_vehicle_id())
                            .vehicle_number(nearVehicle.getNear_vehicle_number())
                            .model(nearVehicle.getNear_vehicle_model())
                            .color(nearVehicle.getNear_vehicle_color())
                            .exitTime(nearVehicle.getNear_vehicle_departuretime())
                            .isLongTermParking(nearVehicle.getNo_departure())
                            .isSatisfied(myDepartureTime.isBefore(nearVehicle.getNear_vehicle_departuretime()));

                    // Add your logic here to populate additional fields in the builder if needed
                    // For example:
                    // builder.additionalField(nearVehicle.getAdditionalField());

                    return builder.build();
                })
                .collect(Collectors.toList());
    }
}
