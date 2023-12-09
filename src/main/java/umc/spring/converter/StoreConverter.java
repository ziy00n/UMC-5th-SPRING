package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {
    public static StoreResponseDTO.createReviewRes toCreateReviewResDTO(Review review) {
        return StoreResponseDTO.createReviewRes.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(StoreRequestDTO.createReviewReq request) {
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
//                .store(store)
//                .member(member)
                .build();
    }

    public static StoreResponseDTO.addStoreRes toAddStoreResDTO(Store store) {
        return StoreResponseDTO.addStoreRes.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(Region region, FoodCategory foodCategory, StoreRequestDTO.addStoreReq request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .score(request.getScore())
                .region(region)
                .foodCategory(foodCategory)
                .build();
    }
}
