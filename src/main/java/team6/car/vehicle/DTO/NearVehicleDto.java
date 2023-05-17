package team6.car.vehicle.DTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class NearVehicleDto {
    @ApiModelProperty(example = "A123456")
    @ApiParam(value = "차량 번호")
    private String vehicle_number;
    @ApiModelProperty(example = "마차")
    @ApiParam(value = "차량 모델")
    private String model;
    @ApiModelProperty(example = "검은색")
    @ApiParam(value = "차량 색상")
    private String color;
    @ApiModelProperty(example = "08:30")
    @ApiParam(value = "출차 시간 hh:mm")
    private String exitTime;
    @ApiModelProperty(example="장기 주차 여부")
    @ApiParam(value = "false")
    private Boolean isLongTermParking;
    @ApiModelProperty(example = "true")
    @ApiParam(value = "해당 차량 앞에 이중주차를 해도 되는가")
    private boolean isSatisfied;
}
