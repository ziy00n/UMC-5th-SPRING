package umc.spring.web.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class addMissionReq {
        @NotNull
        Integer reward;
        @NotNull
        LocalDate deadline;
        @NotNull
        Integer price;
        /*@NotBlank
        String missionSpec;*/
    }
}