package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.enums.UserStatus;
import umc.spring.domain.mapping.*;
import umc.spring.domain.qna.Qna;
import umc.spring.domain.review.Review;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTerm> userTermList = new ArrayList<UserTerm>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<UserMission>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserFavor> userFavorList = new ArrayList<UserFavor>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<Review>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Qna> qnaList = new ArrayList<Qna>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAlarmSetting> alarmSettingList = new ArrayList<UserAlarmSetting>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAlarm> alarmList = new ArrayList<UserAlarm>();
}
