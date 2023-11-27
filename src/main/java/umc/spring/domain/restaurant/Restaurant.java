package umc.spring.domain.restaurant;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.Address;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.review.Review;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 50)
    private String specAddress;

    private Float score;

    // 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOOD_ID")
    private FoodCategory foodCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    // 연관관계 편의 메소드
    public void setAddress(Address address) {
        if (this.address != null) {
            this.address.getRestaurantList().remove(this);
        }
        this.address = address;
        address.getRestaurantList().add(this);
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory=foodCategory;
    }

}
