package team6.car.vehicle.repository;
import org.springframework.stereotype.Repository;
import team6.car.vehicle.domain.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepositoryImpl {
    @PersistenceContext
    private final EntityManager em;
    public VehicleRepositoryImpl(EntityManager em){
        this.em=em;
    }

    public Optional<Vehicle> findByMemberId(Long member_id) {
        // PK 즉 필수 키가 아니면 중복과 부재의 문제가 있으므로 쿼리를 작성해야함
        List<Vehicle> result = em.createQuery("select m from Vehicle_info m where m.member.member_id = :member_id",Vehicle.class)
                .setParameter("member_id", member_id)
                .getResultList();
        return result.stream().findAny();
    }
}
