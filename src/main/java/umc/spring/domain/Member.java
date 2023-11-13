package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.enums.MemberStatus;
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
public class Member extends BaseEntity {

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

    @Column(nullable = false)
    private Integer point;

    private LocalDate birth;

    @Column(nullable = false,length = 50)
    private String specAddress;

    @Column(nullable = false,length = 15)
    private String phone;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean phoneAuth;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus memberStatus;

    private LocalDate inactiveDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberTerm> userTermList = new ArrayList<MemberTerm>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<MemberMission>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFavor> memberFavorList = new ArrayList<MemberFavor>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<Review>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Qna> qnaList = new ArrayList<Qna>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAlarmSetting> alarmSettingList = new ArrayList<MemberAlarmSetting>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAlarm> alarmList = new ArrayList<MemberAlarm>();
}
