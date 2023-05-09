package team6.car.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.car.vehicle.domain.Near_Vehicle;

import java.util.List;
import java.util.Optional;

@Repository
public interface NearVehicleRepository extends JpaRepository<Near_Vehicle, Long> {

    Near_Vehicle save(Near_Vehicle near_vehicle); // 주변 차량 저장
    Optional<Near_Vehicle> findById(Long id);

    Optional<Near_Vehicle> findByDeviceId(Long id);
    List<Near_Vehicle> findAll();
}
