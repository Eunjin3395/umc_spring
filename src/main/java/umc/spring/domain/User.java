package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.enums.UserStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private Integer point;

    private LocalDate birth;

    private String specAddress;

    private String phone;

    private boolean phoneAuth;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    private LocalDate inactiveDate;
}
