package org.keumann.template.service;

import lombok.RequiredArgsConstructor;
import org.keumann.template.repository.TimeRecordRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeRecordService {

    private final TimeRecordRepository timeRecordRepository;

}
