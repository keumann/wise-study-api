package org.keumann.template.member.controller;

import lombok.RequiredArgsConstructor;
import org.keumann.template.admin.member.service.MemberService;
import org.keumann.template.domain.Member;
import org.keumann.template.member.model.KakaoProfile;
import org.keumann.template.member.model.OauthToken;
import org.keumann.template.member.service.KakaoOauthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KakaoLoginController {

    private final MemberService memberService;
    private final KakaoOauthService kakaoOauthService;

    @GetMapping("/auth/kakao/callback")
    public ResponseEntity loginCallback(String code){  //코드를 받음. 정상적으로 로그인이 됐다고 판단하면 됨

        OauthToken oauthToken = kakaoOauthService.getOauthToken(code);
        KakaoProfile kakaoProfile = kakaoOauthService.getProfile(oauthToken);
        Member member = memberService.getMember(kakaoProfile.getKakao_account().getEmail());

        //회원가입
        if(member == null){
            member = kakaoOauthService.createMember(kakaoProfile);
        }

        //로그인처리
        UserDetails ckUserDetails = memberService.loadUserByUsername(member.getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(ckUserDetails, "USER_PASSWORD", ckUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

}
