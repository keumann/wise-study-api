package org.keumann.template.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.keumann.template.dto.TimeRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TimeRecordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void register() throws Exception {

        TimeRecordDto.Register register = new TimeRecordDto.Register();
        register.setStudyTypeName("study");
        register.setSeconds(1000L);
        String json = new ObjectMapper().writeValueAsString(register);

        mockMvc.perform(post("/api/time-record")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

}