package org.keumann.template.repository;

import org.keumann.template.domain.Member;
import org.keumann.template.domain.StudyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyTypeRepository extends JpaRepository<StudyType, Long> {

    Optional<StudyType> findByMemberAndName(Member member, String name);
}
