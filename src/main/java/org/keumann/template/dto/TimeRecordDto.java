package org.keumann.template.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class TimeRecordDto {

    @Getter @Setter
    public static class Register {
        @NotEmpty
        private String studyTypeName;
        @Min(0)
        private Long seconds;
    }

    @Getter @Setter
    public static class DetailResponse {
        private Long id;
        private String studyDate;
        private Long seconds;
        private String StudyTypeName;
    }

}
