package team6.car.vehicle.DTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class VehicleDto {
    @ApiModelProperty(example = "B123456")
    @ApiParam(value = "차량 번호")
    private String vehicle_number;
    @ApiModelProperty(example = "마차")
    @ApiParam(value = "차량 모델")
    private String model;
    @ApiModelProperty(example = "검정색")
    @ApiParam(value = "차량 색상")
    private String color;
    @ApiModelProperty(example = "08:40:00")
    @ApiParam(value = "출차 시간 hh:mm:ss")
    private LocalTime exitTime;
    @ApiModelProperty(example = "false")
    @ApiParam(value = "장기 주차 여부")
    private boolean isLongTermParking;
    @ApiModelProperty(example = "101동 102호")
    @ApiParam(value = "아파트 동/호수")
    private String address;

    public VehicleDto() {
        // 기본 생성자
    }
    public VehicleDto(String vehicle_number, String model, String color, LocalTime exitTime, boolean isLongTermParking, String address) {
        this.vehicle_number = vehicle_number;
        this.model = model;
        this.color = color;
        this.exitTime = exitTime;
        this.isLongTermParking = isLongTermParking;
        this.address = address;
    }

}