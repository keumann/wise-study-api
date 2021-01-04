package org.keumann.template.service;

import lombok.RequiredArgsConstructor;
import org.keumann.template.domain.Member;
import org.keumann.template.domain.StudyType;
import org.keumann.template.repository.StudyTypeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyTypeService {

    private final StudyTypeRepository studyTypeRepository;

    public StudyType getOrCreate(Member member, String name) {

        return studyTypeRepository.findByMemberAndName(member, name)
                .orElseGet(() -> {
                    StudyType studyType = StudyType.builder()
                            .member(member)
                            .name(name)
                            .build();
                    return studyTypeRepository.save(studyType);
                });

    }

}
