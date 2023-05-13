package team6.car.apartment.service;

import team6.car.apartment.DTO.ApartmentNoticeDto;
import team6.car.apartment.domain.ApartmentNotice;

import java.util.List;
import java.util.Optional;

public interface ApartmentNoticeService {
    List<ApartmentNoticeDto> getApartmentNotice(Long id);
    List<ApartmentNotice> findByApartmentId(Long apartmentId);
}
