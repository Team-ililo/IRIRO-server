package team6.car.device.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import team6.car.device.domain.Near_Device;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class NearDeviceRepositoryImpl implements NearDeviceRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Near_Device save(Near_Device near_device){
        em.persist(near_device);
        return near_device;
    }
    public List<Near_Device> findAll() {
        String query = "SELECT nd FROM Near_Device_info nd";
        TypedQuery<Near_Device> typedQuery = em.createQuery(query, Near_Device.class);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Near_Device> findById(Long id) {
        return Optional.ofNullable(em.find(Near_Device.class, id));
    }

    @Override
    public <S extends Near_Device, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Optional<Near_Device> findByDevice(Long device_id) {
        String query = "SELECT nd FROM Near_Device_info nd WHERE nd.device_id = :device_id";
        TypedQuery<Near_Device> typedQuery = em.createQuery(query, Near_Device.class);
        typedQuery.setParameter("device_id", device_id);
        List<Near_Device> nearDevices = typedQuery.getResultList();
        if (nearDevices.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(nearDevices.get(0));
        }
    }

    @Override
    public <S extends Near_Device> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Near_Device> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Near_Device> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Near_Device> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public List<Near_Device> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Near_Device> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public Page<Near_Device> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void flush(){

    }

    @Override
    public <S extends Near_Device> S saveAndFlush(S entity){
        return null;
    }

    @Override
    public <S extends Near_Device> List<S> saveAllAndFlush(Iterable<S> entities) {
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
    public void deleteAllInBatch(Iterable<Near_Device> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Near_Device getOne(Long aLong) {
        return null;
    }

    @Override
    public Near_Device getById(Long aLong) {
        return null;
    }

    @Override
    public Near_Device getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Near_Device> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Near_Device> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends Near_Device> List<S> saveAll(Iterable<S> entities) {
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
    public void delete(Near_Device entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Near_Device> entities) {

    }

    @Override
    public void deleteAll() {

    }

}
