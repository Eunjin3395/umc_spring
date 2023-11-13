package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MISSION_ID")
    private Long id;

    private Integer amount;

    private Integer point;

    private String description;

    private LocalDate dueDate;
}
