package umc.spring.service.StoreService;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Review createReview(Long storeId, Long memberId, StoreRequestDTO.createReviewReq request);
    Store addStore(Long regionId, StoreRequestDTO.addStoreReq request);
    Mission addMission(Long storeId, MissionRequestDTO.addMissionReq request);
}
