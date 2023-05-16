package team6.car.apartment.DTO;

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
public class ApartmentDto {
    @ApiModelProperty(example = "푸르지오")
    @ApiParam(value = "아파트 이름")
    private String name;

}
