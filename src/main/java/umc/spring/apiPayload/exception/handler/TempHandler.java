package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode); // 부모 클래스(GeneralException)의 생성자 호출: errorCode를 파라미터로 받아 GeneralException의 code 값 넣어 예외 생성
    }
}
