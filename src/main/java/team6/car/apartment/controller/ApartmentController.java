package team6.car.apartment.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import team6.car.apartment.DTO.ApartmentNoticeDto;
import team6.car.apartment.domain.ApartmentNotice;
import team6.car.apartment.response.Message;
import team6.car.apartment.response.StatusEnum;
import team6.car.apartment.service.ApartmentNoticeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ApartmentController {

    @Autowired
    private final ApartmentNoticeService apartmentNoticeService;

    @ApiOperation(value = "아파트 공지사항 조회", notes = "아파트 공지사항 리스트 조회")
    @ApiImplicitParam(
            name = "apartmentName",
            value = "아파트 이름",
            required = true,
            dataType = "String",
            paramType = "path"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK(아파트 공지사항 조회가 완료되었습니다.)"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 404, message = "NOT_FOUND(해당 아파트에 대한 공지사항이 없습니다.)"),
            @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR(아파트 공지사항 조회에 실패하였습니다.)")
    })
    @GetMapping("/apartment/{apartmentName}")
    public ResponseEntity<Message<List<ApartmentNoticeDto>>> getApartmentNotice(@PathVariable String apartmentName) {
        List<ApartmentNoticeDto> apartmentNoticeDtos;
        StatusEnum status;
        String message;

        try {
            Optional<List<ApartmentNoticeDto>> apartmentNoticesOptional = apartmentNoticeService.getApartmentNotice(apartmentName);
            if (apartmentNoticesOptional.isPresent()) {
                apartmentNoticeDtos = apartmentNoticesOptional.get();

                // null 값을 체크하여 실패로 처리
                boolean hasNullValues = apartmentNoticeDtos.stream()
                        .anyMatch(notice -> notice.getApartment_notice_date() == null || notice.getNotice() == null);

                if (hasNullValues) {
                    apartmentNoticeDtos = null;
                    status = StatusEnum.NOT_FOUND;
                    message = "공지사항 정보가 유효하지 않습니다.";
                } else {
                    status = StatusEnum.OK;
                    message = "아파트 공지사항 조회가 완료되었습니다.";
                }
            } else {
                apartmentNoticeDtos = null;
                status = StatusEnum.NOT_FOUND;
                message = "해당 아파트에 대한 공지사항이 없습니다.";
            }
        } catch (RuntimeException e) {
            apartmentNoticeDtos = null;
            status = StatusEnum.INTERNAL_SERVER_ERROR;
            message = "아파트 공지사항 조회에 실패하였습니다.";
        }

        Message<List<ApartmentNoticeDto>> responseMessage = new Message<>();
        responseMessage.setStatus(status);
        responseMessage.setMessage(message);
        responseMessage.setData(apartmentNoticeDtos);

        return ResponseEntity.status(status.getStatusCode()).body(responseMessage);
    }
}