package team6.car.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team6.car.device.domain.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
