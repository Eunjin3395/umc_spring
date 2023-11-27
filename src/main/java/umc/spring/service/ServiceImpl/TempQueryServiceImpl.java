package umc.spring.service.ServiceImpl;

import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.TempHandler;
import umc.spring.service.TempQueryService;

@Service
public class TempQueryServiceImpl implements TempQueryService {
    @Override
    public void checkFlag(Integer flag) {
        if (flag == 2) {
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
