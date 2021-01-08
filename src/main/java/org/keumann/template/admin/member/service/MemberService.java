package org.keumann.template.admin.member.service;

import lombok.RequiredArgsConstructor;
import org.keumann.template.domain.Member;
import org.keumann.template.domain.MemberRole;
import org.keumann.template.exception.BusinessException;
import org.keumann.template.member.dto.MemberDto;
import org.keumann.template.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember.isPresent()){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(email));

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRoles().toString())
                .build();
    }

    private void validateCreateMember(MemberDto memberDto){
        validateUserId(memberDto);
    }

    private void validateUserId(MemberDto memberDto){
        Member member = memberRepository.findMemberByEmail(memberDto.getEmail());

        if(member != null)
            throw new BusinessException("이미 가입된 회원아이디입니다.", HttpStatus.CONFLICT.value());
    }

    private Member enrollKakaoMember(MemberDto memberDto){
        MemberRole memberRole = new MemberRole();
        memberRole.setRoleName("USER");
        memberDto.setRoles(Collections.singletonList(memberRole));
        Member member = modelMapper.map(memberDto, Member.class);
        return memberRepository.save(member);
    }

    public Member createMember(MemberDto memberDto) throws BusinessException{
        this.validateCreateMember(memberDto);
        Member member = null;
        member = enrollKakaoMember(memberDto);
        return member;
    }

    @Transactional(readOnly = true)
    public Member getMember(String email) throws RuntimeException {
        return memberRepository.findMemberByEmail(email);
    }

}
