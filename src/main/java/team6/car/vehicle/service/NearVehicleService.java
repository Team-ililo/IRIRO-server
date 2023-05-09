package team6.car.vehicle.service;

import team6.car.vehicle.domain.Near_Vehicle;

import java.time.LocalDateTime;

public interface NearVehicleService {

    Near_Vehicle enrollDeparturetime(Long id, LocalDateTime exitTime, Boolean isLongTermParking);

    Near_Vehicle modifyDeparturetime(Long id, LocalDateTime exitTime, Boolean isLongTermParking);
    Near_Vehicle getDeparturetime(Long id);
}
