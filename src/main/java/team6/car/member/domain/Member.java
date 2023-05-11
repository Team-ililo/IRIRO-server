package team6.car.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import team6.car.apartment.domain.Apartment;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Member_info")
@Table(name="Member_info")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;
    @Column(name="name")
    private String name;
    @Column(name="phone_number")
    private String phone_number;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="address")
    private String address;
    @Column(name="number_of_complaints")
    private int number_of_complaints;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) //many = member, one = apartment 하나의 아파트는 여러 명의 멤버를 가질 수 있다
    @JoinColumn(name="apartment_id") //FK
    private Apartment apartment;
}
