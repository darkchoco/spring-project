package darkchoco.spring.narasrest.repository;

import darkchoco.spring.narasrest.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByCommonNameContainingIgnoreCase(String commonName);
}
