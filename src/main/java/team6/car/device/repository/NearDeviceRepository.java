package team6.car.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.car.device.domain.NearDevice;

import java.util.Optional;

@Repository
public interface NearDeviceRepository extends JpaRepository<NearDevice, String> {
    Optional<NearDevice> findById(String id);

    Optional<NearDevice> findByDeviceId(String device_id);

    Optional<NearDevice> findByNearDeviceId(String device_id);
}
