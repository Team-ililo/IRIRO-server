package team6.car.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.car.device.domain.Device;
import team6.car.device.domain.Near_Device;

import java.util.Optional;

@Repository
public interface NearDeviceRepository extends JpaRepository<Near_Device, Long> {
    Optional<Near_Device> findByDevice(Long device_id);
}
