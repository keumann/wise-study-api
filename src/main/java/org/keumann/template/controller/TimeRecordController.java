package org.keumann.template.controller;

import lombok.RequiredArgsConstructor;
import org.keumann.template.service.TimeRecordService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TimeRecordController {

    private final TimeRecordService timeRecordService;

}
