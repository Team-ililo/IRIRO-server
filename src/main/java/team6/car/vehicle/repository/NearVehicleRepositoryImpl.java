package team6.car.vehicle.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import team6.car.vehicle.domain.NearVehicle;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@Primary
public class NearVehicleRepositoryImpl implements NearVehicleRepository {

    @PersistenceContext
    private final EntityManager em;
    public NearVehicleRepositoryImpl(EntityManager em){
        this.em=em;
    }

    @Override
    public NearVehicle save(NearVehicle near_vehicle){
        em.persist(near_vehicle);
        return near_vehicle;
    }

    @Override
    public <S extends NearVehicle, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
    @Override
    public Optional<NearVehicle> findById(Long id) {
        return Optional.ofNullable(em.find(NearVehicle.class, id));
    }

    @Override
    public Optional<NearVehicle> findByNearDeviceId(Long id) {
        TypedQuery<NearVehicle> query = em.createQuery(
                "SELECT nv FROM Near_Vehicle_info nv WHERE nv.near_device_id = :near_device_id",
                NearVehicle.class);
        query.setParameter("near_device_id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<NearVehicle> findByNearDeviceDeviceIdAndNoDepartureIsFalse(Long device_id) {
        String query = "SELECT nv FROM Near_Vehicle_info nv JOIN nv.near_device nd WHERE nd.near_device_id = :device_id AND nv.No_departure = false";
        TypedQuery<NearVehicle> typedQuery = em.createQuery(query, NearVehicle.class);
        typedQuery.setParameter("device_id", device_id);
        return typedQuery.getResultList();
    }

    @Override
    public List<NearVehicle> findAll() {
        return em.createQuery("SELECT nv FROM Near_Vehicle_info nv", NearVehicle.class)
                .getResultList();
    }

    @Override
    public <S extends NearVehicle> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends NearVehicle> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends NearVehicle> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends NearVehicle> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public List<NearVehicle> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<NearVehicle> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public Page<NearVehicle> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void flush(){

    }

    @Override
    public <S extends NearVehicle> S saveAndFlush(S entity){
        return null;
    }

    @Override
    public <S extends NearVehicle> List<S> saveAllAndFlush(Iterable<S> entities) {
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
    public void deleteAllInBatch(Iterable<NearVehicle> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public NearVehicle getOne(Long aLong) {
        return null;
    }

    @Override
    public NearVehicle getById(Long aLong) {
        return null;
    }

    @Override
    public NearVehicle getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends NearVehicle> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends NearVehicle> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends NearVehicle> List<S> saveAll(Iterable<S> entities) {
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
    public void delete(NearVehicle entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends NearVehicle> entities) {

    }

    @Override
    public void deleteAll() {

    }

}
