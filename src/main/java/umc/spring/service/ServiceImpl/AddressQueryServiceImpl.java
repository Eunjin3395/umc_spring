package umc.spring.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.AddressRepository;
import umc.spring.service.AddressQueryService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressQueryServiceImpl implements AddressQueryService {
    private final AddressRepository addressRepository;
    @Override
    public boolean isExistAddress(Long id) {
        return addressRepository.existsById(id);
    }
}
