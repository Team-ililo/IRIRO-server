package team6.car.member.DTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @ApiModelProperty(example = "김정통")
    @ApiParam(value = "이름")
    private String name;
    @ApiModelProperty(example = "01011112222")
    @ApiParam(value = "핸드폰 번호")
    private String phone_number;
    @ApiModelProperty(example = "C123123")
    @ApiParam(value = "차량 번호")
    private String vehicle_number;
    @ApiModelProperty(example = "마차")
    @ApiParam(value = "차량 모델")
    private String vehicle_model;
    @ApiModelProperty(example = "검정색")
    @ApiParam(value = "차량 색상")
    private String vehicle_color;
    @ApiModelProperty(example = "푸르지오")
    @ApiParam(value = "아파트 이름")
    private String apartment_name;
    @ApiModelProperty(example = "103동 103호")
    @ApiParam(value = "아파트 동/호수")
    private String address;
    @ApiModelProperty(example = "IRIRO@capstone.com")
    @ApiParam(value = "이메일 아이디")
    private String email;
    @ApiModelProperty(example = "password_IRIRO")
    @ApiParam(value = "비밀번호")
    private String password;
    @ApiModelProperty(example = "password_IRIRO")
    @ApiParam(value = "비밀번호 재확인")
    private String pw_check;
    @ApiModelProperty(example = "111")
    @ApiParam(value = "디바이스 ID")
    private Long device_id;
}
