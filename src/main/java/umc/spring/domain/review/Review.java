package umc.spring.domain.review;

import lombok.*;
import umc.spring.domain.Member;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.restaurant.Restaurant;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float score;

    @Column(nullable = false,length = 2000)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    // 연관관계 편의 메소드
    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getReviewList().remove(this);
        }
        this.member = member;
        this.member.getReviewList().add(this);
    }

    public void setRestaurant(Restaurant restaurant) {
        if (this.restaurant != null) {
            this.restaurant.getReviewList().remove(this);
        }
        this.restaurant = restaurant;
        this.restaurant.getReviewList().add(this);
    }
}
