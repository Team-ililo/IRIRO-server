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
public class LoginDto {
    @ApiModelProperty(example = "IRIRO@capstone.com")
    @ApiParam(value = "이메일 아이디")
    private String email;
    @ApiModelProperty(example = "password_IRIRO")
    @ApiParam(value = "비밀번호")
    private String password;
}
