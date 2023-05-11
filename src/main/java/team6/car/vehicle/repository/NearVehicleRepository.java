package team6.car.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.car.vehicle.domain.NearVehicle;

import java.util.List;
import java.util.Optional;

@Repository
public interface NearVehicleRepository extends JpaRepository<NearVehicle, Long> {

    NearVehicle save(NearVehicle near_vehicle); // 주변 차량 저장
    Optional<NearVehicle> findById(Long id);
    List<NearVehicle> findByNearDeviceDeviceIdAndNoDepartureIsFalse(Long deviceId);

    Optional<NearVehicle> findByNearDeviceId(Long id);
    Optional<List<NearVehicle>> findByDeviceId(Long device_id);
    List<NearVehicle> findAll();
}
