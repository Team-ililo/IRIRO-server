package team6.car.member.DTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class getReportDto {
    @ApiModelProperty(example = "등록한 출차 시간 이전에 차를 빼달라고 요구함")
    @ApiParam(value = "신고 내용")
    private List<String> complaint_contents;
    @ApiModelProperty(example = "1")
    @ApiParam(value = "신고 당한 횟수")
    private int number_of_complaints;
}
