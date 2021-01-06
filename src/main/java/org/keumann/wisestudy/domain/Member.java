package org.keumann.wisestudy.domain;

import lombok.*;
import org.keumann.wisestudy.admin.member.dto.MemberFormDto;
import org.keumann.wisestudy.constant.Role;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter
@ToString
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

    @Builder
    public Member(String name, String email, String password, String address, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

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
