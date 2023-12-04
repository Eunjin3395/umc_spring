package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {
        Gender gender = null;

        switch (request.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        LocalDate birth = LocalDate.of(request.getBirthYear(), request.getBirthMonth(), request.getBirthDay());

        return Member.builder()
                .address(null) // request의 addressId를 통해 address 엔티티 찾아서 넣어야함
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .nickname(request.getName())
                .birth(birth)
                .phone(request.getPhone())
                .memberFavorList(new ArrayList<>())
                .build();

    }

    public static MemberResponseDTO.StartMissionResultDTO toStartMissionResultDTO(MemberMission memberMission) {
        return MemberResponseDTO.StartMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    public static MemberMission toMemberMission(MissionStatus status) {
        return MemberMission.builder()
                .status(status).build();
    }

    public static MemberResponseDTO.memberMissionPreViewDTO toOngoingMissionPreViewDTO(MemberMission memberMission) {
        return MemberResponseDTO.memberMissionPreViewDTO.builder()
                .restaurantName(memberMission.getMission().getRestaurant().getName())
                .description(memberMission.getMission().getDescription())
                .point(memberMission.getMission().getPoint())
                .dueDate(memberMission.getMission().getDueDate())
                .createdAt(memberMission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberResponseDTO.memberMissionPreViewDTO toCompletedMissionPreViewDTO(MemberMission memberMission) {
        return MemberResponseDTO.memberMissionPreViewDTO.builder()
                .restaurantName(memberMission.getMission().getRestaurant().getName())
                .description(memberMission.getMission().getDescription())
                .point(memberMission.getMission().getPoint())
                .updatedAt(memberMission.getUpdatedAt().toLocalDate())
                .build();
    }

    public static MemberResponseDTO.memberMissionPreViewListDTO toMemberMissionPreViewListDTO(MissionStatus status,Page<MemberMission> memberMissionList) {
        List<MemberResponseDTO.memberMissionPreViewDTO> memberMissionPreViewList =new ArrayList<>();
        if (status == MissionStatus.ONGOING) {
            memberMissionPreViewList = memberMissionList.stream()
                    .map(MemberConverter::toOngoingMissionPreViewDTO).collect(Collectors.toList());
        } else if (status == MissionStatus.COMPLETED) {
            memberMissionPreViewList = memberMissionList.stream()
                    .map(MemberConverter::toCompletedMissionPreViewDTO).collect(Collectors.toList());
        }


        return MemberResponseDTO.memberMissionPreViewListDTO.builder()
                .status(status.toString())
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(memberMissionPreViewList.size())
                .missionList(memberMissionPreViewList)
                .build();
    }
}
