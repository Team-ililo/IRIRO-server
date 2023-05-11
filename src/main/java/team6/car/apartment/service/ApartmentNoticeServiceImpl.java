package team6.car.apartment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team6.car.apartment.DTO.ApartmentNoticeDto;
import team6.car.apartment.domain.ApartmentNotice;
import team6.car.apartment.repository.ApartmentNoticeRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApartmentNoticeServiceImpl implements ApartmentNoticeService{

    @Autowired
    private final ApartmentNoticeRepository apartmentNoticeRepository;

    @Override
    public ApartmentNoticeDto getApartmentNotice(Long id) {
        ApartmentNotice apartmentNotice = apartmentNoticeRepository.findByApartmentId(id)
                .orElseThrow(() -> new RuntimeException("아파트 정보를 찾을 수 없습니다."));

        return ApartmentNoticeDto.builder()
                .apartment_notice_id(apartmentNotice.getApartment_notice_id())
                .apartment_notice_date(apartmentNotice.getApartment_notice_date())
                .notice(apartmentNotice.getApartment_notice())
                .build();
    }

    @Override
    public Optional<ApartmentNotice> findByApartmentId(Long apartment_id) {
        return apartmentNoticeRepository.findByApartmentId(apartment_id);
    }
}