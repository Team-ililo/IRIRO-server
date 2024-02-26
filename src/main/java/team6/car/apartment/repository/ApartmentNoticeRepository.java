package team6.car.apartment.repository;

import team6.car.apartment.domain.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import team6.car.apartment.domain.ApartmentNotice;

import java.util.List;
import java.util.Optional;

public interface ApartmentNoticeRepository extends JpaRepository<ApartmentNotice, Long> {

    Optional<ApartmentNotice> findById(Long id);
    List<ApartmentNotice> findByApartment_ApartmentId(Long apartmentId);
    //Optional<ApartmentNotice> findByApartmentId(Long apartmentId);
    List<ApartmentNotice> findByApartment_ApartmentName(String apartmentName);
}
