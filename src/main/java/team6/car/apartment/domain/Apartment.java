
package team6.car.apartment.domain;

import lombok.*;
import team6.car.member.domain.Member;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Apartment_info")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apartmentId;
    @Column(name="apartment_name")
    private String apartmentName;
    @OneToMany(mappedBy = "apartment")
    private List<Member> MemberInfo;
}
