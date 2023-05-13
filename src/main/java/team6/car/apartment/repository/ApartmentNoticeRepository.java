package team6.car.apartment.repository;

import team6.car.apartment.domain.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import team6.car.apartment.domain.ApartmentNotice;

import java.util.List;
import java.util.Optional;

public interface ApartmentNoticeRepository extends JpaRepository<ApartmentNotice, Long> {

    ApartmentNotice save(ApartmentNotice apartmentNotice);
    Optional<ApartmentNotice> findById(Long id);
    List<ApartmentNotice> findByApartmentId(Long apartmentId);
    //Optional<ApartmentNotice> findByApartmentId(Long apartmentId);
}
