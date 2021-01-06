package org.keumann.wisestudy;

import org.keumann.wisestudy.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
        return args -> {
//            Member member = Member.builder()
//                    .email("admin@co.kr")
//                    .password("1111")
//                    .address("test")
//                    .name("admin")
//                    .role(Role.ADMIN)
//                    .build();
//
//            memberRepository.save(member);
        };
    }

}
