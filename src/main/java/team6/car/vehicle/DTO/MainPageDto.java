package team6.car.vehicle.DTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
@Builder
public class MainPageDto {
    @ApiModelProperty(example = "08:30")
    @ApiParam(value = "출차 시간 hh:mm")
    private String exitTime;
    @ApiModelProperty(example = "12:15")
    @ApiParam(value = "출차 시간까지의 남은 시간 hh:mm")
    private String remainingTime;
    @ApiModelProperty(example = "false")
    @ApiParam(value = "장기 주차 여부")
    private Boolean isLongTermParking;
    @ApiModelProperty(example = "푸르지오")
    @ApiParam(value = "아파트 이름")
    private String apartmentName;
    @ApiModelProperty(example = "101동 101호")
    @ApiParam(value = "아파트 동/호수")
    private String address;

}