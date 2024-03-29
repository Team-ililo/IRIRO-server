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

    private final ApartmentNoticeRepository apartmentNoticeRepository;
/*
    @Override
    public Optional<List<ApartmentNoticeDto>> getApartmentNotice(Long id) {
        List<ApartmentNotice> apartmentNotices = apartmentNoticeRepository.findByApartment_ApartmentId(id);
        if (apartmentNotices.isEmpty()) {
            throw new RuntimeException("아파트 정보를 찾을 수 없습니다.");
        }
        List <ApartmentNoticeDto> noticeDtos = apartmentNotices.stream()
                .map(apartmentNotice -> ApartmentNoticeDto.builder()
                        .apartment_notice_date(apartmentNotice.getApartmentNoticeDate())
                        .notice(apartmentNotice.getApartmentNotice())
                        .build())
                .collect(Collectors.toList());
        return Optional.of(noticeDtos);
    }
*/

    @Override
    public Optional<List<ApartmentNoticeDto>> getApartmentNotice(String apartmentName) {
        List<ApartmentNotice> apartmentNotices = apartmentNoticeRepository.findByApartment_ApartmentName(apartmentName);
        if (apartmentNotices.isEmpty()) {
            return Optional.empty();
        }
        List<ApartmentNoticeDto> noticeDtos = apartmentNotices.stream()
                .map(apartmentNotice -> ApartmentNoticeDto.builder()
                        .apartment_notice_date(apartmentNotice.getApartmentNoticeDate())
                        .notice(apartmentNotice.getApartmentNotice())
                        .build())
                .collect(Collectors.toList());
        return Optional.of(noticeDtos);
    }


}