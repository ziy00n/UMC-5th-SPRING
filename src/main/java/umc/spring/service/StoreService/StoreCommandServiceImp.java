package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.*;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.repository.*;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.StoreRequestDTO;


@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImp implements StoreCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final MissionRepository missionRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    //@Transactional
    public Review createReview(Long storeId, Long memberId, StoreRequestDTO.createReviewReq request) {
        Review newReview = StoreConverter.toReview(request);
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        newReview.setStore(store);
        newReview.setMember(member);
/*        Store store = storeRepository.getReferenceById(storeId);
        Member member = memberRepository.getReferenceById(memberId);
        Review newReview = StoreConverter.toReview(request);*/
        return reviewRepository.save(newReview);
    }

    @Override
    public Store addStore(Long regionId, StoreRequestDTO.addStoreReq request) {
        /*Optional<Region> optionalRegion = regionRepository.findById(regionId);
        if (optionalRegion.isPresent()) {
            Store newStore = StoreConverter.toStore(request, optionalRegion.get());
            return storeRepository.save(newStore);
        }*/

        Region region = regionRepository.findById(regionId).orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));
        FoodCategory foodCategory = foodCategoryRepository.findById(request.getFoodCategory()).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
        Store newStore = StoreConverter.toStore(region, foodCategory, request);
        return storeRepository.save(newStore);
    }

    @Override
    public Mission addMission(Long storeId, MissionRequestDTO.addMissionReq request) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new MissionHandler(ErrorStatus.STORE_NOT_FOUND));
        Mission newMission = MissionConverter.toMission(store, request);
        return missionRepository.save(newMission);
    }
}
