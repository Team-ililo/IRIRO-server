package team6.car.device.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import team6.car.device.domain.Device;
import team6.car.member.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DeviceRepositoryImpl implements DeviceRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Device> findAll() {
        return null;
    }

    @Override
    public List<Device> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Device> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Device> findAllById(Iterable<Long> longs) {
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
    public void delete(Device entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Device> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Device save(Device device) {
        em.persist(device);
        return null;
    }

    @Override
    public <S extends Device> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Device> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Device> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Device> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Device> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Device getOne(Long aLong) {
        return null;
    }

    @Override
    public Device getById(Long aLong) {
        return null;
    }

    @Override
    public Device getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Device> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Device> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Device> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Device> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Device> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Device> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Device, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
