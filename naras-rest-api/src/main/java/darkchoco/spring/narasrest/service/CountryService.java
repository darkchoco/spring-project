package darkchoco.spring.narasrest.service;

import darkchoco.spring.narasrest.domain.Country;
import darkchoco.spring.narasrest.dto.CountryDTO;
import darkchoco.spring.narasrest.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryDTO findById(String code) {
        Country entity = countryRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Not found country: " + code));
        return new CountryDTO(entity);
    }

    // TODO: Need to improve to avoid N-times query
    public List<CountryDTO> findAll() {
        return countryRepository.findAll()
                .stream()
                .map(CountryDTO::new)
                .toList();
    }

    public List<CountryDTO> findByCommonNameContaining(String name) {
        return countryRepository.findByCommonNameContainingIgnoreCase(name)
                .stream()
                .map(CountryDTO::new)
                .toList();
    }
}
