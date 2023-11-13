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
    private Long id;

    @Column(nullable = false, length=15)
    private String nickname;

    @Column(nullable = false,length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15)")
    private SocialType socialType;

    private Integer point;

    private LocalDate birth;

    @Column(nullable = false,length = 50)
    private String specAddress;

    @Column(nullable = false,length = 15)
    private String phone;

    @Column(nullable = false, columnDefinition = "DEFAULT 0")
    private boolean phoneAuth;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
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
