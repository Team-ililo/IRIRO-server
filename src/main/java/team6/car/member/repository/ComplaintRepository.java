package team6.car.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.car.member.domain.Complaint;
import team6.car.member.domain.Member;
import team6.car.vehicle.domain.Vehicle;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplaintRepository  extends JpaRepository<Complaint, Long> {
    Complaint save(Complaint complaint);
    Optional<Complaint> findComplaintByMemberId(Long member_id);
    List<Complaint> findByMemberId(Long memberId);
}
