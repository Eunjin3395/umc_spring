package umc.spring.domain.restaurant;

import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Day;

import javax.persistence.*;

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

    @Embedded
    private OpeningHours openingHours;
}
