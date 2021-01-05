package org.keumann.template.service;

import lombok.RequiredArgsConstructor;
import org.keumann.template.constant.Role;
import org.keumann.template.domain.Member;
import org.keumann.template.domain.StudyType;
import org.keumann.template.domain.TimeRecord;
import org.keumann.template.dto.TimeRecordDto;
import org.keumann.template.repository.MemberRepository;
import org.keumann.template.repository.TimeRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TimeRecordService {

    private final TimeRecordRepository timeRecordRepository;
    private final StudyTypeService studyTypeService;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public Long register(TimeRecordDto.Register registerDto, Member member) {

        //TODO: 로그인 적용 후 제거
        Member tMember = Member.builder()
                .email("admin@co.kr")
                .password("1111")
                .address("test")
                .name("admin")
                .role(Role.ADMIN)
                .build();
        memberRepository.save(tMember);

        LocalDate now = LocalDate.now();
        StudyType studyType = studyTypeService.getOrCreate(member, registerDto.getStudyTypeName());
        TimeRecord timeRecord = TimeRecord.builder()
                .member(member)
                .studyType(studyType)
                .seconds(registerDto.getSeconds())
                .studyDate(now)
                .day(now.getDayOfWeek())
                .build();

        TimeRecord savedTimeRecord = timeRecordRepository.save(timeRecord);
        return savedTimeRecord.getId();
    }

    public TimeRecordDto.DetailResponse findById(Long id) {
        TimeRecord timeRecord = timeRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(""));

        return modelMapper.map(timeRecord, TimeRecordDto.DetailResponse.class);
    }
}
