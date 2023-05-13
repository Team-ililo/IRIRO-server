package team6.car.apartment.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import team6.car.apartment.domain.ApartmentNotice;
import team6.car.vehicle.domain.Vehicle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ApartmentNoticeRepositoryImpl implements ApartmentNoticeRepository{
    @PersistenceContext
    private final EntityManager em;
    public ApartmentNoticeRepositoryImpl(EntityManager em){
        this.em=em;
    }

    @Override
    public ApartmentNotice save(ApartmentNotice apartmentNotice){
        em.persist(apartmentNotice);
        return apartmentNotice;
    }

    @Override
    public <S extends ApartmentNotice, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
    @Override
    public Optional<ApartmentNotice> findById(Long id) {
        return Optional.ofNullable(em.find(ApartmentNotice.class, id));
    }

    @Override
    public List<ApartmentNotice> findByApartmentId(Long apartment_id) {
        String query = "SELECT an FROM ApartmentNotice an WHERE an.apartment.apartment_id = :apartment_id";
        TypedQuery<ApartmentNotice> typedQuery = em.createQuery(query, ApartmentNotice.class);
        typedQuery.setParameter("apartment_id", apartment_id);

        return typedQuery.getResultList();
    }

    @Override
    public List<ApartmentNotice> findAll() {
        return em.createQuery("SELECT a FROM Apartment_Notice_info a", ApartmentNotice.class)
                .getResultList();
    }

    @Override
    public <S extends ApartmentNotice> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ApartmentNotice> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ApartmentNotice> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ApartmentNotice> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public List<ApartmentNotice> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<ApartmentNotice> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public Page<ApartmentNotice> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void flush(){

    }

    @Override
    public <S extends ApartmentNotice> S saveAndFlush(S entity){
        return null;
    }

    @Override
    public <S extends ApartmentNotice> List<S> saveAllAndFlush(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (S entity : entities) {
            result.add(saveAndFlush(entity));
        }
        flush();
        return result;
    }

    @Override
    public void deleteAllInBatch(Iterable<ApartmentNotice> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ApartmentNotice getOne(Long aLong) {
        return null;
    }

    @Override
    public ApartmentNotice getById(Long aLong) {
        return null;
    }

    @Override
    public ApartmentNotice getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends ApartmentNotice> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ApartmentNotice> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends ApartmentNotice> List<S> saveAll(Iterable<S> entities) {
        return null;
    }
    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(ApartmentNotice entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ApartmentNotice> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
