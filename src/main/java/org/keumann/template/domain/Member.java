package org.keumann.template.domain;

import lombok.*;
import org.keumann.template.admin.member.dto.MemberFormDto;
import org.keumann.template.constant.Role;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity{

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;
    public static Member createAdmin(MemberFormDto memberFormDto) {
        return Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .address(memberFormDto.getAddress())
                .password(memberFormDto.getPassword())
                .role(Role.ADMIN)
                .build();
    }
}
