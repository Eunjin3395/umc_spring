package umc.spring.domain.qna;

import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.QnaStatus;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Qna extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private QnaStatus status;

    private String title;

    private String contents;
}
