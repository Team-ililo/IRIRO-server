package team6.car.apartment.DTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentNoticeDto {
    @ApiModelProperty(example = "2023-05-17")
    @ApiParam(value = "아파트 공지 시간")
    private LocalDate apartment_notice_date;
    @ApiModelProperty(example = "공지사항 내용")
    @ApiParam(value = "아파트 공지사항")
    private String notice;
}
