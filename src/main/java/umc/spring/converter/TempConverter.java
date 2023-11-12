package umc.spring.converter;

import umc.spring.web.dto.TempResponse;

public class TempConverter {

    public static TempResponse.TempTestDTO toTempTestDTO() {
        return TempResponse.TempTestDTO.builder()
                .testString("테스트임미다.^_^_^^..")
                .build();
    }
}
