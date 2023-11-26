package umc.spring.converter;

import umc.spring.domain.Address;
import umc.spring.domain.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
}
