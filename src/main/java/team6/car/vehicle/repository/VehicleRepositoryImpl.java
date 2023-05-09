package team6.car.vehicle.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import team6.car.vehicle.domain.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    private final EntityManager em;
    public VehicleRepositoryImpl(EntityManager em){
        this.em=em;
    }

    @Override
    public Vehicle save(Vehicle vehicle){
        em.persist(vehicle);
        return vehicle;
    }

    @Override
    public <S extends Vehicle, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
    @Override
    public Optional<Vehicle> findById(Long id) {
        return Optional.ofNullable(em.find(Vehicle.class, id));
    }

    @Override
    public Optional<Vehicle> findByIdWithMember(Long id) {
        return Optional.ofNullable(em.createQuery(
                        "SELECT v FROM Vehicle_info v JOIN FETCH v.member WHERE v.id=:id",Vehicle.class)
                .setParameter("id",id)
                .getSingleResult());
    }

    @Override
    public List<Vehicle> findAll() {
        return em.createQuery("SELECT v FROM Vehicle_info v", Vehicle.class)
                .getResultList();
    }

    @Override
    public <S extends Vehicle> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Vehicle> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Vehicle> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Vehicle> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public List<Vehicle> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Vehicle> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public Page<Vehicle> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void flush(){

    }

    @Override
    public <S extends Vehicle> S saveAndFlush(S entity){
        return null;
    }

    @Override
    public <S extends Vehicle> List<S> saveAllAndFlush(Iterable<S> entities) {
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
    public void deleteAllInBatch(Iterable<Vehicle> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Vehicle getOne(Long aLong) {
        return null;
    }

    @Override
    public Vehicle getById(Long aLong) {
        return null;
    }

    @Override
    public Vehicle getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Vehicle> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Vehicle> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends Vehicle> List<S> saveAll(Iterable<S> entities) {
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
    public void delete(Vehicle entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Vehicle> entities) {

    }

    @Override
    public void deleteAll() {

    }

}
