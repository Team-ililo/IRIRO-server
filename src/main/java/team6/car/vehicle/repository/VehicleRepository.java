package team6.car.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team6.car.vehicle.domain.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByMemberId(Long userId);
    Optional<Vehicle> findByVehicleNumber(String vehicle_number);
}
