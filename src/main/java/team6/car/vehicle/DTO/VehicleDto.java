package team6.car.vehicle.DTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    /*
    @ApiModelProperty(example = "B123456")
    @ApiParam(value = "차량 번호")
    private String vehicle_number;
    @ApiModelProperty(example = "마차")
    @ApiParam(value = "차량 모델")
    private String vehicle_model;
    @ApiModelProperty(example = "검정색")
    @ApiParam(value = "차량 색상")
    private String color;
    @ApiModelProperty(example = "101동 102호")
    @ApiParam(value = "아파트 동/호수")
    private String address;
    */

    @ApiModelProperty(example = "08:40:00")
    @ApiParam(value = "출차 시간 hh:mm:ss")
    private LocalTime exitTime;
    @ApiModelProperty(example = "false")
    @ApiParam(value = "장기 주차 여부")
    private Boolean no_departure;
}