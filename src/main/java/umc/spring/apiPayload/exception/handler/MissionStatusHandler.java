package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class MissionStatusHandler extends GeneralException {
    public MissionStatusHandler(BaseErrorCode code) {
        super(code);
    }
}
