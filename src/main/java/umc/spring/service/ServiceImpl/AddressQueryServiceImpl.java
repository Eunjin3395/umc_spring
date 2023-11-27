package umc.spring.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.AddressRepository;
import umc.spring.service.AddressQueryService;

@Service
@RequiredArgsConstructor
public class AddressQueryServiceImpl implements AddressQueryService {
    private final AddressRepository addressRepository;
    @Override
    public boolean isExistAddress(Long id) {
        return addressRepository.existsById(id);
    }
}
