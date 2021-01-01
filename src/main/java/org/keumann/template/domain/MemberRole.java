package org.keumann.template.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member_role")
@EqualsAndHashCode(of = "")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRole extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String roleName;

}
