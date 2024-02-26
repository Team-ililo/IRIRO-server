package team6.car.apartment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team6.car.apartment.domain.Apartment;
import team6.car.apartment.repository.ApartmentRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private final ApartmentRepository apartmentRepository;

    @Override
    public Long getApartmentIdByName(String apartmentName) {
        Optional<Apartment> existingApartment = apartmentRepository.findByApartmentName(apartmentName);
        if (existingApartment.isPresent()) {
            return existingApartment.get().getApartmentId();
        } else {
            Apartment newApartment = new Apartment();
            newApartment.setApartmentName(apartmentName);
            apartmentRepository.save(newApartment);
            return newApartment.getApartmentId();
        }
    }
}