package team6.car.member.repository;

import org.springframework.transaction.annotation.Transactional;
import team6.car.apartment.domain.Apartment;
import team6.car.member.domain.Member;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository //레포지토리 스프링 빈으로 등록
public class MemberRepositoryImpl implements MemberRepository {
    @PersistenceUnit
    EntityManagerFactory emf;
    @PersistenceContext
    private final EntityManager em;
    public MemberRepositoryImpl(EntityManager em){
        this.em=em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member);
        return null;
    }
    @Transactional /**수정 이후 save**/
    public Member update(Member member) {
        return this.em.merge(member);
    }

    /**회원 정보 수정(신고당한 횟수 1 증가)**/
    @Transactional
    public Member updateComplaintNumber(Member updatedMember) {
        EntityManager em = emf.createEntityManager();

        try {
            // 수정할 회원 엔티티를 불러옵니다.
            Member member = em.find(Member.class, updatedMember.getMember_id());

            // 회원 정보를 수정합니다.
            member.setName(updatedMember.getName());
            member.setPhone_number(updatedMember.getPhone_number());
            member.setEmail(updatedMember.getEmail());
            member.setEmail(updatedMember.getEmail());
            member.setPassword(updatedMember.getPassword());
            member.setNumber_of_complaints(updatedMember.getNumber_of_complaints()+1);
            member.setApartment(updatedMember.getApartment());

            // EntityManager의 merge() 메소드를 이용하여 수정된 엔티티를 저장합니다.
            em.merge(member);
            // 수정된 회원 정보를 반환합니다.
            return member;
        } finally {
            em.close();
        }
    }
    @Override
    public Optional<Member> findById(Long id) { //member_id
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    @Override
    public Optional<Member> findByEmail(String email) { //멤버 이메일
        // PK 즉 필수 키가 아니면 중복과 부재의 문제가 있으므로 쿼리를 작성해야함
        List<Member> result = em.createQuery("select m from Member_info m where m.email = :email",Member.class)
                .setParameter("email", email)
                .getResultList();
        return result.stream().findAny();
    }
    public void deleteMember(Long member_id) {
        Member member = em.find(Member.class, member_id);
        if (member != null) {
            em.remove(member);
        }
    }
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member_info m",Member.class)
                .getResultList();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Member> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Member> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Member> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Member getOne(Long aLong) {
        return null;
    }

    @Override
    public Member getById(Long aLong) {
        return null;
    }

    @Override
    public Member getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Member> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Member> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Member> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Member> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Member> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Member> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Member, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Member> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Member> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public <S extends Member> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
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
    public void delete(Member entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Member> entities) {

    }

    @Override
    public void deleteAll() {

    }
}

