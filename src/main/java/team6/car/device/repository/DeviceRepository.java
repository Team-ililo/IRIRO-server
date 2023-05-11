package team6.car.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.car.device.domain.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
