package team6.car.vehicle.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;
import team6.car.device.domain.Device;
import team6.car.device.domain.NearDevice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="NearVehicle")
@Table(name="near_vehicle_info")
public class NearVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long near_vehicle_id;
    @Column(name="near_vehicle_number")
    private String near_vehicle_number;
    @Column(name="near_vehicle_model")
    private String near_vehicle_model;
    @Column(name="near_vehicle_color")
    private String near_vehicle_color;
    @Column(name="near_vehicle_departuretime")
    private LocalTime near_vehicle_departuretime;
    @Column(name="no_departure")
    private Boolean No_departure;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="near_device_id") //FK
    private NearDevice near_device;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="device_id") //FK
    private Device device;

    // 필드에 대한 getter 메서드 추가
    public Long getNear_vehicle_id() {
        return near_vehicle_id;
    }

    public String getNear_vehicle_number() {
        return near_vehicle_number;
    }

    public String getNear_vehicle_model() {
        return near_vehicle_model;
    }

    public String getNear_vehicle_color() {
        return near_vehicle_color;
    }

    public LocalTime getNear_vehicle_departuretime() {
        return near_vehicle_departuretime;
    }

    public Boolean getNo_departure() {
        return No_departure;
    }

    public NearDevice getNear_device() {
        return near_device;
    }

    public Device getDevice() {
        return device;
    }

}
