package darkchoco.spring.narasrest.service;

import darkchoco.spring.narasrest.domain.Country;
import darkchoco.spring.narasrest.dto.CountryDTO;
import darkchoco.spring.narasrest.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;

@RequiredArgsConstructor
@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryDTO findById(String code) {
        Country entity = countryRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Not found country: " + code));
        return new CountryDTO(entity);
    }

    public List<CountryDTO> findAll() {
        // TreeMap을 사용하여 commonName으로 정렬 (대소문자 구분 없이)
        TreeMap<String, CountryDTO> countryMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        // 모든 국가를 가져와 DTO로 변환 후 맵에 추가
        for (Country country : countryRepository.findAll()) {
            CountryDTO dto = new CountryDTO(country);
            countryMap.putIfAbsent(dto.getCommonName(), dto);  // 중복된 키가 있을 경우 기존 값을 유지
        }

        // 정렬된 맵의 값들을 리스트로 변환하여 반환
        return List.copyOf(countryMap.values());
    }

    public List<CountryDTO> findByCommonNameContaining(String name) {
        return countryRepository.findByCommonNameContainingIgnoreCase(name)
                .stream()
                .map(CountryDTO::new)
                .toList();
    }
}
