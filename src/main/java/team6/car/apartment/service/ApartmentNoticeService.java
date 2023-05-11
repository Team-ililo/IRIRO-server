package team6.car.apartment.service;

import team6.car.apartment.DTO.ApartmentNoticeDto;
import team6.car.apartment.domain.ApartmentNotice;

import java.util.Optional;

public interface ApartmentNoticeService {
    ApartmentNoticeDto getApartmentNotice(Long id);
    Optional<ApartmentNotice> findByApartmentId(Long apartmentId);
}
