package team6.car.member.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private String name;
    private String phone_number;
    private String vehicle_number;
    private String vehicle_model;
    private String vehicle_color;
    private String apartment_name;
    private String address;
    private String email;
    private String password;
    private String pw_check;
    private String device_id;
}
