package org.keumann.template.domain;

import lombok.*;
import org.keumann.template.constant.Day;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="time_record")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeRecord extends BaseEntity{

    @Id
    @Column(name="time_record_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate studyDate;

    private Day day;

    private Long seconds;

    @OneToOne
    @JoinColumn(name = "study_type_id")
    private StudyType studyType;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
