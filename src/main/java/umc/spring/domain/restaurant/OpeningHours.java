package umc.spring.domain.restaurant;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
@Getter
public class OpeningHours {
    @Temporal(TemporalType.TIME)
    private Date openTime;

    @Temporal(TemporalType.TIME)
    private Date closeTime;

    protected OpeningHours(){}

    public OpeningHours(Date openTime, Date closeTime) {
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
