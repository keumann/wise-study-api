package org.keumann.template.saying.service;

import lombok.RequiredArgsConstructor;
import org.keumann.template.domain.WiseSaying;
import org.keumann.template.repository.WiseSayingRepository;
import org.keumann.template.saying.dto.WiseSayingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WiseSayingService {

    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingDto getWiseSayingDto(Long id){

        if(id == null){
            return new WiseSayingDto();
        } else{
            WiseSaying wiseSaying = wiseSayingRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new);
            return WiseSayingDto.of(wiseSaying);
        }

    }

    @Transactional
    public Long saveWiseSaying(WiseSayingDto wiseSayingDto){
        WiseSaying wiseSaying;

        if(wiseSayingDto.getId() == null){
            wiseSaying = WiseSaying.createWiseSaying(wiseSayingDto);
            wiseSayingRepository.save(wiseSaying);
        } else{
            wiseSaying = wiseSayingRepository.findById(wiseSayingDto.getId())
                    .orElseThrow(EntityNotFoundException::new);
            wiseSaying.updateWiseSaying(wiseSayingDto);
        }

        return wiseSaying.getId();
    }

    public Page<WiseSaying> getWiseSayingList(Pageable pageable){
        Page<WiseSaying> wiseSayings = wiseSayingRepository.findAll(pageable);
        return wiseSayings;
    }

}
