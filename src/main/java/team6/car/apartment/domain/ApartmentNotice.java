package team6.car.apartment.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Apartment_Notice_info")
public class ApartmentNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apartment_notice_id;
    @CreatedDate
    @Column(name="apartment_notice_date", updatable = false)
    private LocalDate apartment_notice_date;

    @Column(name="apartment_notice")
    private String apartment_notice;
    @ManyToOne(fetch = FetchType.LAZY) //many = Apartment notice, one = apartment 하나의 아파트는 여러 개의 공지를 가질 수 있다
    @JoinColumn(/*referencedColumnName="apartment_id"*/ name="apartment_id") //FK
    private Apartment apartment;
}
