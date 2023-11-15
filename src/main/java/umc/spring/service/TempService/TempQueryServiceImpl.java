package umc.spring.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.TempHandler;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService{

    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1) {
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION); // flag가 1인 경우 TEMP_EXCEPTION 예외를 발생
        }
    }
}
