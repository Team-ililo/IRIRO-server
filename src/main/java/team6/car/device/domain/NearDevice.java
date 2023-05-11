package team6.car.device.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="NearDevice")
@Table(name="near_device_info")
public class NearDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long near_device_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="device_id") //FK
    private Device device;
}
