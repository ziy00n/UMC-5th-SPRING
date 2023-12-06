package umc.spring.service.StoreService;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Review createReview(Long storeId, Long memberId, StoreRequestDTO.ReviewDTO request);
    Store addStore(Long regionId, StoreRequestDTO.StoreDTO request);
}
