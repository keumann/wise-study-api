package org.keumann.template.saying.dto;

import lombok.*;
import org.keumann.template.domain.WiseSaying;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class WiseSayingDto {

    private Long id;

    @NotBlank
    private String content;

    private String nativeContent;

    @NotBlank
    private String person;

    private static ModelMapper modelMapper = new ModelMapper();

    public static WiseSayingDto of(WiseSaying wiseSaying) {
        return modelMapper.map(wiseSaying, WiseSayingDto.class);
    }
}
