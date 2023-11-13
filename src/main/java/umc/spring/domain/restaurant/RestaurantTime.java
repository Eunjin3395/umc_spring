package umc.spring.domain.restaurant;

import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Day;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RestaurantTime extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Day day;

    @Temporal(TemporalType.TIME)
    private Date openTime;

    @Temporal(TemporalType.TIME)
    private Date closeTime;

//    @Embedded
//    private OpeningHours openingHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;
}
