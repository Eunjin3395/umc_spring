package umc.spring.domain.enums;

import lombok.AllArgsConstructor;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;

@AllArgsConstructor
public enum MissionStatus {
    ONGOING("ONGOING"),
    COMPLETED("COMPLETED");

    private final String status;

    public static MissionStatus of(String statusInput) {
        if (statusInput == null) {
            throw new GeneralException(ErrorStatus.MISSION_STATUS_NOT_FOUND);
        }

        for (MissionStatus missionStatus : MissionStatus.values()) {
            if (missionStatus.status.equals(statusInput.toUpperCase())) {
                return missionStatus;
            }
        }

        throw new GeneralException(ErrorStatus.MISSION_STATUS_NOT_FOUND);
    }

}
