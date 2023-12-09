package umc.spring.web.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StoreRequestDTO {

    @Getter
    public static class createReviewReq {
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotBlank
        String body;
    }

    @Getter
    public static class addStoreReq {
        @NotBlank
        String name;
        @NotBlank
        String address;
        @NotNull
        Float score;
        @NotNull
        Long foodCategory;
    }
}
