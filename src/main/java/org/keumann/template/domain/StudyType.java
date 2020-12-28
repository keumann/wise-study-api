package org.keumann.template.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="study_type")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudyType  extends BaseEntity{

    @Id
    @Column(name = "study_type_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
