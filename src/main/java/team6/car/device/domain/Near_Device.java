package team6.car.device.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Near_Device_info")
@Table(name="Near_Device_info")
public class Near_Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long near_device_id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="device_id") //FK
    private Device device;
}
