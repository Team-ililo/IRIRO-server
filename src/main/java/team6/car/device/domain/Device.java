package team6.car.device.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Device")
@Table(name="device_info")
public class Device {
    @Id
    private Long device_id;
}
