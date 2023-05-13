package team6.car.member.DTO;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberProfileDto {
    private String email;
    private String vehicle_number;
    private String address;
    private int number_of_complaints;
}
