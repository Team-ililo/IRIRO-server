package team6.car.apartment.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import team6.car.apartment.DTO.ApartmentNoticeDto;
import team6.car.apartment.response.Message;
import team6.car.apartment.response.StatusEnum;
import team6.car.apartment.service.ApartmentNoticeService;

@RequiredArgsConstructor
@RestController
public class ApartmentController {

    @Autowired
    private final ApartmentNoticeService apartmentNoticeService;

    @GetMapping("/apartment/{id}")
    public ResponseEntity<Message<ApartmentNoticeDto>> getApartmentNotice(@PathVariable Long id) {
        ApartmentNoticeDto apartmentNoticeDto;
        StatusEnum status;
        String message;

        try {
            apartmentNoticeDto = apartmentNoticeService.getApartmentNotice(id);
            status = StatusEnum.OK;
            message = "아파트 공지사항 조회가 완료되었습니다.";
        } catch (RuntimeException e) {
            apartmentNoticeDto = null;
            status = StatusEnum.INTERNAL_SERVER_ERROR;
            message = "아파트 공지사항 조회에 실패하였습니다.";
        }

        Message<ApartmentNoticeDto> responseMessage = new Message<>();
        responseMessage.setStatus(status);
        responseMessage.setMessage(message);
        responseMessage.setData(apartmentNoticeDto);

        return ResponseEntity.status(status.getStatusCode()).body(responseMessage);
    }
}