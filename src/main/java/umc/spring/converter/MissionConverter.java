package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

public class MissionConverter {
    public static MissionResponseDTO.addMissionRes toCreateMissionResDTO(Mission mission) {
        return MissionResponseDTO.addMissionRes.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static Mission toMission(Store  store, MissionRequestDTO.addMissionReq request) {
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .price(request.getPrice())
                .store(store)
                //.missionSpec(request.getMissionSpec())
                .build();
    }
}
