package team6.car.device.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Device_info")
@Table(name="Device_info")
public class Device {
    @Id
    private Long device_id;
}
