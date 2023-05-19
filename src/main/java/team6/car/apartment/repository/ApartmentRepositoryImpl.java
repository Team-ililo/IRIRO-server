package team6.car.apartment.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import team6.car.apartment.domain.Apartment;
import team6.car.apartment.domain.ApartmentNotice;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class ApartmentRepositoryImpl implements ApartmentRepository {

    @PersistenceContext
    private EntityManager em;
    public ApartmentRepositoryImpl(EntityManager em){
        this.em=em;
    }

    @Override
    public <S extends Apartment, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
    @Override
    public Optional<Apartment> findById(Long id) {
        return Optional.ofNullable(em.find(Apartment.class, id));
    }

    @Override
    public <S extends Apartment> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Apartment> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Apartment> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Apartment> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public List<Apartment> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Apartment> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public Page<Apartment> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Apartment> S save(S entity) {
        em.persist(entity);
        return entity;
    }
    @Override
    public void flush(){

    }

    @Override
    public <S extends Apartment> S saveAndFlush(S entity){
        return null;
    }

    @Override
    public <S extends Apartment> List<S> saveAllAndFlush(Iterable<S> entities) {
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
    public void deleteAllInBatch(Iterable<Apartment> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Apartment getOne(Long aLong) {
        return null;
    }

    @Override
    public Apartment getById(Long aLong) {
        return null;
    }

    @Override
    public Apartment getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Apartment> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Apartment> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends Apartment> List<S> saveAll(Iterable<S> entities) {
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
    public void delete(Apartment entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Apartment> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Apartment> findAll() {
        return em.createQuery("SELECT a FROM Apartment a", Apartment.class)
                .getResultList();
    }
    @Override
    public Optional<Apartment> findByApartment_name(String apartment_name) {
        TypedQuery<Apartment> query = em.createQuery("SELECT a FROM Apartment a WHERE a.apartment_name = :apartment_name", Apartment.class);
        query.setParameter("apartment_name", apartment_name);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}