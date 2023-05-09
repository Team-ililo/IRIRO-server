package team6.car.apartment.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import team6.car.apartment.DTO.ApartmentNoticeDto;
import team6.car.apartment.service.ApartmentNoticeService;

@RequiredArgsConstructor
@RestController
public class ApartmentController {

    @Autowired
    private final ApartmentNoticeService apartmentNoticeService;

    @GetMapping("/apartment/{id}")
    public ResponseEntity<ApartmentNoticeDto> getApartmentNotice(@PathVariable Long id){
        ApartmentNoticeDto apartmentNoticeDto=apartmentNoticeService.getApartmentNotice(id);
        return ResponseEntity.ok(apartmentNoticeDto);
    }
}
