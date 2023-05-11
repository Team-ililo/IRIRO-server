package team6.car.apartment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import team6.car.apartment.DTO.ApartmentNoticeDto;
import team6.car.apartment.service.ApartmentNoticeService;

@Api(tags = {"ApartmentController"})
@RequiredArgsConstructor
@RestController
public class ApartmentController {

    private final ApartmentNoticeService apartmentNoticeService;

    @ApiOperation(value = "아파트 공지사항 및 공지시간 조회", notes = "아파트의 공지사항 및 공지시간을 조회한다")
    @ApiImplicitParam(name = "member_id", value = "회원 아이디")
    @GetMapping("/apartment/{id}")
    public ResponseEntity<ApartmentNoticeDto> getApartmentNotice(@PathVariable Long id){
        ApartmentNoticeDto apartmentNoticeDto=apartmentNoticeService.getApartmentNotice(id);
        return ResponseEntity.ok(apartmentNoticeDto);
    }
}
