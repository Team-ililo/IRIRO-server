package team6.car.member.DTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    @ApiModelProperty(example = "등록한 출차 시간 이전에 차를 빼달라고 요구")
    @ApiParam(value = "신고 내용")
    private String complaint_contents;
    @ApiModelProperty(example = "B123123")
    @ApiParam(value = "차량 번호")
    private String vehicle_number;
}
