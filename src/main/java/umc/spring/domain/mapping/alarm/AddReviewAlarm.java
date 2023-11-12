package umc.spring.domain.mapping.alarm;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("R")
public class AddReviewAlarm extends UserAlarm{
}
