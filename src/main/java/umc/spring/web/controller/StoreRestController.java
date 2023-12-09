package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{storeId}/reviews/{memberId}")
    public ApiResponse<StoreResponseDTO.createReviewRes> createReview(@RequestBody @Valid StoreRequestDTO.createReviewReq request,
                                                                      @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                      @ExistMember @PathVariable(name = "memberId") Long memberId) {
        Review newReview = storeCommandService.createReview(storeId, memberId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResDTO(newReview));
    }

    @PostMapping("/{regionId}")
    public ApiResponse<StoreResponseDTO.addStoreRes> addStore(@RequestBody @Valid StoreRequestDTO.addStoreReq request,
                                                              @PathVariable Long regionId) {
        Store newStore = storeCommandService.addStore(regionId, request);
        return ApiResponse.onSuccess(StoreConverter.toAddStoreResDTO(newStore));
    }
}
