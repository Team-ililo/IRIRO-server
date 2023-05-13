package team6.car.member.repository;
import org.springframework.stereotype.Repository;

import team6.car.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //인터페이스란 기능에 대한 명세 집합
    //공통적인 함수들을 정의하고 실질적인 repository에 상속 받는다
    Member save(Member member); //회원을 저장소에 저장
    Member update(Member member);
    Member updateComplaintNumber(Member updatedMember);
    Optional<Member> findById(Long id); //id로 받고 member 반환
    Optional<Member> findByEmail(String email); //ID(=email)로 받고 member 반환
    void deleteMember(Long member_id);
    List<Member> findAll();
}
