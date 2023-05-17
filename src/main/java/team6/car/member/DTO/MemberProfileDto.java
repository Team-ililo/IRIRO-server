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
public class MemberProfileDto {
    @ApiModelProperty(example = "IRIRO@capstone.com")
    @ApiParam(value = "이메일 아이디")
    private String email;
    @ApiModelProperty(example = "A123123")
    @ApiParam(value = "차량 번호")
    private String vehicle_number;
    @ApiModelProperty(example = "101동 103호")
    @ApiParam(value = "아파트 동/호수")
    private String address;
    @ApiModelProperty(example = "0")
    @ApiParam(value = "신고 당한 횟수")
    private int number_of_complaints;
}
