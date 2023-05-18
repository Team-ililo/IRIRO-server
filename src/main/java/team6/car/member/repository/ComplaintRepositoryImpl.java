package team6.car.member.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import team6.car.member.DTO.MemberProfileDto;
import team6.car.member.domain.Complaint;
import team6.car.member.domain.Member;
import team6.car.vehicle.domain.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class ComplaintRepositoryImpl implements ComplaintRepository{
    @PersistenceContext
    private final EntityManager em;
    public ComplaintRepositoryImpl(EntityManager em){
        this.em=em;
    }
    @Override
    public Complaint save(Complaint complaint) {
        em.persist(complaint);
        return null;
    }

    /**회원 id로 정보 조회 (신고 당한 내용)**/
    @Override
    public Optional<Complaint> findComplaintByMemberId(Long member_id) {
        // PK 즉 필수 키가 아니면 중복과 부재의 문제가 있으므로 쿼리를 작성해야함
        List<Complaint> result = em.createQuery("select m from Complaint_info m where m.member.member_id = :member_id",Complaint.class)
                .setParameter("member_id", member_id)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Complaint> findByMemberId(Long member_id) {
        List<Complaint> result = em.createQuery("select m from Complaint_info m where m.member.member_id = :member_id",Complaint.class)
                .setParameter("member_id", member_id)
                .getResultList();
        return result;
    }

    @Override
    public List<Complaint> findAll() {
        return null;
    }

    @Override
    public List<Complaint> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Complaint> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Complaint> findAllById(Iterable<Long> longs) {
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
    public void delete(Complaint entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Complaint> entities) {

    }

    @Override
    public void deleteAll() {

    }
    @Override
    public <S extends Complaint> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Complaint> findById(Long aLong) {
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
    public <S extends Complaint> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Complaint> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Complaint> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Complaint getOne(Long aLong) {
        return null;
    }

    @Override
    public Complaint getById(Long aLong) {
        return null;
    }

    @Override
    public Complaint getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Complaint> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Complaint> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Complaint> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Complaint> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Complaint> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Complaint> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Complaint, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
