package team6.car.apartment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Apartment_Notice_info")
@EntityListeners(AuditingEntityListener.class)
@Table(name="Apartment_Notice_info")
public class ApartmentNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apartment_notice_id;
    @CreatedDate
    @Column(name="apartment_notice_date", updatable = false)
    private LocalDateTime apartment_notice_date;
    @Column(name="apartment_notice")
    private String apartment_notice;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) //many = Apartment notice, one = apartment 하나의 아파트는 여러 개의 공지를 가질 수 있다
    @JoinColumn(name="apartment_id") //FK
    private Apartment apartment;
}
