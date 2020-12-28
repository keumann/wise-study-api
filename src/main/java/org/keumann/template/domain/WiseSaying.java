package org.keumann.template.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="time_record")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WiseSaying extends BaseEntity{

    @Id
    @Column(name = "wise_saying_id")
    @GeneratedValue
    private Long id;

    @Lob
    private String content;

    @Lob
    private String nativeContent;

    private String person;

}
