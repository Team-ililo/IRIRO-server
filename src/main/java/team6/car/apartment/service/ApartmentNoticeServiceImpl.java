package team6.car.apartment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team6.car.apartment.DTO.ApartmentNoticeDto;
import team6.car.apartment.domain.ApartmentNotice;
import team6.car.apartment.repository.ApartmentNoticeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ApartmentNoticeServiceImpl implements ApartmentNoticeService{

    @Autowired
    private final ApartmentNoticeRepository apartmentNoticeRepository;

    @Override
    public List<ApartmentNoticeDto> getApartmentNotice(Long id) {
        List<ApartmentNotice> apartmentNotices = apartmentNoticeRepository.findByApartmentId(id);
        if (apartmentNotices.isEmpty()) {
            throw new RuntimeException("아파트 정보를 찾을 수 없습니다.");
        }
        return apartmentNotices.stream()
                .map(apartmentNotice -> ApartmentNoticeDto.builder()
                        .apartment_notice_date(apartmentNotice.getApartment_notice_date())
                        .notice(apartmentNotice.getApartment_notice())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentNotice> findByApartmentId(Long apartment_id) {
        List<ApartmentNotice> apartmentNotices = apartmentNoticeRepository.findByApartmentId(apartment_id);
        if (apartmentNotices.isEmpty()) {
            throw new RuntimeException("아파트 공지사항을 찾을 수 없습니다.");
        }
        return apartmentNotices;
    }
}