package team6.car.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team6.car.vehicle.domain.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
