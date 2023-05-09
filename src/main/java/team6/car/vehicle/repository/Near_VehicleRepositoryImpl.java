package team6.car.vehicle.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import team6.car.vehicle.domain.Near_Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class Near_VehicleRepositoryImpl implements Near_VehicleRepository {

    @PersistenceContext
    private final EntityManager em;
    public Near_VehicleRepositoryImpl(EntityManager em){
        this.em=em;
    }

    @Override
    public Near_Vehicle save(Near_Vehicle near_vehicle){
        em.persist(near_vehicle);
        return near_vehicle;
    }

    @Override
    public <S extends Near_Vehicle, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
    @Override
    public Optional<Near_Vehicle> findById(Long id) {
        return Optional.ofNullable(em.find(Near_Vehicle.class, id));
    }

    @Override
    public Optional<Near_Vehicle> findByDeviceId(Long id) {
        TypedQuery<Near_Vehicle> query = em.createQuery(
                "SELECT nv FROM Near_Vehicle nv WHERE nv.deviceId = :deviceId",
                Near_Vehicle.class);
        query.setParameter("deviceId", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Near_Vehicle> findAll() {
        return em.createQuery("SELECT nv FROM Near_Vehicle nv", Near_Vehicle.class)
                .getResultList();
    }

    @Override
    public <S extends Near_Vehicle> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Near_Vehicle> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Near_Vehicle> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Near_Vehicle> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public List<Near_Vehicle> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Near_Vehicle> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public Page<Near_Vehicle> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void flush(){

    }

    @Override
    public <S extends Near_Vehicle> S saveAndFlush(S entity){
        return null;
    }

    @Override
    public <S extends Near_Vehicle> List<S> saveAllAndFlush(Iterable<S> entities) {
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
    public void deleteAllInBatch(Iterable<Near_Vehicle> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Near_Vehicle getOne(Long aLong) {
        return null;
    }

    @Override
    public Near_Vehicle getById(Long aLong) {
        return null;
    }

    @Override
    public Near_Vehicle getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Near_Vehicle> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Near_Vehicle> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends Near_Vehicle> List<S> saveAll(Iterable<S> entities) {
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
    public void delete(Near_Vehicle entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Near_Vehicle> entities) {

    }

    @Override
    public void deleteAll() {

    }

}
