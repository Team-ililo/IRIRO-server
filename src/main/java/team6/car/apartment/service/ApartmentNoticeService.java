package team6.car.apartment.service;

import team6.car.apartment.DTO.ApartmentNoticeDto;
import team6.car.apartment.domain.ApartmentNotice;

public interface ApartmentNoticeService {
    ApartmentNoticeDto getApartmentNotice(Long id);
}
