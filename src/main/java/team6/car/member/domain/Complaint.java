package team6.car.member.domain;

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
@Entity(name="Complaint_info")
@EntityListeners(AuditingEntityListener.class)
@Table(name="Complaint_info")
public class Complaint {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaint_id;
    @Column(name="complaint_contents")
    private String complaint_contents;
    @CreatedDate
    @Column(name="complaint_date", updatable = false)
    private LocalDateTime complaint_date;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) //many = complaint info, one = member 하나의 멤버는 여러 개의 신고를 가질 수 있다
    @JoinColumn(name="member_id") //FK
    private Member member;
}