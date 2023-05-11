package team6.car.vehicle.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import team6.car.device.domain.Device;
import team6.car.device.domain.Near_Device;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Near_Vehicle_info")
@Table(name="Near_Vehicle_info")
public class NearVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long near_vehicle_id;
    @Column(name="near_vehicle_number")
    private String near_vehicle_number;
    @Column(name="near_vehicle_model")
    private String near_vehicle_model;
    @Column(name="near_vehicle_color")
    private String near_vehicle_color;
    @Column(name="near_vehicle_departuretime")
    private LocalDateTime near_vehicle_departuretime;
    @Column(name="no_departure")
    private Boolean No_departure;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="near_device_id") //FK
    private Near_Device near_device;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="device_id") //FK
    private Device device;

}
