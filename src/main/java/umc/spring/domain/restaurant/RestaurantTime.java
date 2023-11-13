package umc.spring.domain.restaurant;

import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Day;

import javax.persistence.*;
import java.time.LocalTime;
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
    @Column(columnDefinition = "VARCHAR(5)")
    private Day day;

    private LocalTime openTime;

    private LocalTime closeTime;

//    @Embedded
//    private OpeningHours openingHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;
}
