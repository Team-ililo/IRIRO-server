package team6.car.apartment.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentNoticeDto {
    private Long apartment_notice_id;
    private LocalDateTime apartment_notice_date;
    private String notice;
}
