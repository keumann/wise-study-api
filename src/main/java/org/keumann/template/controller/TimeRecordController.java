package org.keumann.template.controller;

import lombok.RequiredArgsConstructor;
import org.keumann.template.annotation.CurrentMember;
import org.keumann.template.domain.Member;
import org.keumann.template.dto.TimeRecordDto;
import org.keumann.template.service.TimeRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/time-record")
@RequiredArgsConstructor
public class TimeRecordController {

    private final TimeRecordService timeRecordService;

    @PostMapping
    public ResponseEntity<Long> registerTimeRecord(
            @CurrentMember Member member,
            @Valid @RequestBody TimeRecordDto.Register registerDto) {

        Long timeRecordId = timeRecordService.register(registerDto, member);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(timeRecordId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTimeRecord(@PathVariable Long id) {
        TimeRecordDto.DetailResponse dto = timeRecordService.findById(id);
        return ResponseEntity.ok(dto);
    }

}
