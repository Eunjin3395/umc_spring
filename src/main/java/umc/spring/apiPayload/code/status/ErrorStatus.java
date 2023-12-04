package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    // 일반 오류
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 오류입니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "권한이 없습니다."),

    // 멤버 관련 오류
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자를 찾을 수 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수입니다."),


    // 게시글 관련 오류
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글을 찾을 수 없습니다."),

    // 미션 관련 오류
    MISSION_ALREADY_STARTED(HttpStatus.BAD_REQUEST,"MISSION4001","이미 진행중인 미션입니다"),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,"MISSION4002","미션 정보를 찾을 수 없습니다"),
    MISSION_STATUS_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4003", "잘못된 미션 상태 값입니다."),

    // 음식 카테고리 관련 오류
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND,"FOOD4001","음식 카테고리를 찾을 수 없습니다."),

    // 음식점 관련 오류
    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "RESTAURANT4001", "음식점을 찾을 수 없습니다."),

    // 주소 관련 오류
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND,"ADDRESS4001","주소 정보를 찾을 수 없습니다."),

    // 테스트용 오류
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "테스트용 에러입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .message(message)
                .code(code)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonStatus() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .httpStatus(httpStatus)
                .message(message)
                .code(code)
                .build();    }
}
