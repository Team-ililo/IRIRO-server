package team6.car.member.DTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnId {
    @ApiModelProperty(example = "1")
    @ApiParam(value = "멤버 아이디")
    private Long member_id;
    @ApiModelProperty(example = "1")
    @ApiParam(value = "아파트 이름")
    private String apartment_name;
    @ApiModelProperty(example = "1")
    @ApiParam(value = "디바이스 아이디")
    private String device_id;
    @ApiModelProperty(example = "1")
    @ApiParam(value = "차량 아이디")
    private Long vehicle_id;
}
