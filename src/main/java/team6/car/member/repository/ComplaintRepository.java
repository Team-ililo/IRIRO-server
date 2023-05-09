package team6.car.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.car.member.domain.Complaint;
import team6.car.member.domain.Member;

@Repository
public interface ComplaintRepository  extends JpaRepository<Complaint, Long> {
}
