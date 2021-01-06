package org.keumann.wisestudy.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="wise_saying")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WiseSaying extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wise_saying_id")
    private Long id;

    @Lob
    private String content;

    @Lob
    private String nativeContent;

    private String person;

}
