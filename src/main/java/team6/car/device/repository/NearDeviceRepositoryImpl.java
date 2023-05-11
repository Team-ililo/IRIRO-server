package team6.car.device.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import team6.car.device.domain.NearDevice;

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
    public NearDevice save(NearDevice near_device){
        em.persist(near_device);
        return near_device;
    }
    public List<NearDevice> findAll() {
        String query = "SELECT nd FROM Near_Device_info nd";
        TypedQuery<NearDevice> typedQuery = em.createQuery(query, NearDevice.class);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<NearDevice> findById(Long id) {
        return Optional.ofNullable(em.find(NearDevice.class, id));
    }

    @Override
    public <S extends NearDevice, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Optional<NearDevice> findByDeviceId(Long device_id) {
        String query = "SELECT nd FROM Near_Device_info nd WHERE nd.device_id = :device_id";
        TypedQuery<NearDevice> typedQuery = em.createQuery(query, NearDevice.class);
        typedQuery.setParameter("device_id", device_id);
        List<NearDevice> nearDevices = typedQuery.getResultList();
        if (nearDevices.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(nearDevices.get(0));
        }
    }

    @Override
    public <S extends NearDevice> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends NearDevice> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends NearDevice> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends NearDevice> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public List<NearDevice> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<NearDevice> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public Page<NearDevice> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void flush(){

    }

    @Override
    public <S extends NearDevice> S saveAndFlush(S entity){
        return null;
    }

    @Override
    public <S extends NearDevice> List<S> saveAllAndFlush(Iterable<S> entities) {
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
    public void deleteAllInBatch(Iterable<NearDevice> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public NearDevice getOne(Long aLong) {
        return null;
    }

    @Override
    public NearDevice getById(Long aLong) {
        return null;
    }

    @Override
    public NearDevice getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends NearDevice> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends NearDevice> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends NearDevice> List<S> saveAll(Iterable<S> entities) {
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
    public void delete(NearDevice entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends NearDevice> entities) {

    }

    @Override
    public void deleteAll() {

    }

}
