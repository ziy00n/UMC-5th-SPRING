package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;


@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImp implements StoreCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;

    @Override
    //@Transactional
    public Review createReview(Long storeId, Long memberId, StoreRequestDTO.ReviewDTO request) {
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
    public Store addStore(Long regionId, StoreRequestDTO.StoreDTO request) {
        /*Optional<Region> optionalRegion = regionRepository.findById(regionId);
        if (optionalRegion.isPresent()) {
            Store newStore = StoreConverter.toStore(request, optionalRegion.get());
            return storeRepository.save(newStore);
        }*/

        Region region = regionRepository.findById(regionId).orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));
        Store newStore = StoreConverter.toStore(request, region);
        return storeRepository.save(newStore);
    }
}
