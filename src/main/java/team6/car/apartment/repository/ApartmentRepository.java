package team6.car.apartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team6.car.apartment.domain.Apartment;
import team6.car.apartment.domain.ApartmentNotice;

import java.util.List;
import java.util.Optional;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    Optional<Apartment> findById(Long id);
    List<Apartment> findAll();

}
