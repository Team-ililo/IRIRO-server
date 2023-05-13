package team6.car.member.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class getReportDto {
    private String complaint_contents;
    private int number_of_complaints;
}
