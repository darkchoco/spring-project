package darkchoco.spring.narasrest.repository;

import darkchoco.spring.narasrest.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByCommonNameContainingIgnoreCase(String commonName);

//    @Query("""
//            SELECT cc.capital, co.code, co.commonName, co.officialName, co.flagEmoji, co.flagImg, co.region, co.population
//            FROM Country co
//            INNER JOIN CountryCapital cc ON co.code = cc.country_code""")
    @Query(value = """
            SELECT cc.capital, co.code, co.common_name, co.official_name, co.flag_emoji, co.flag_img, co.region, co.population
            FROM country co
            INNER JOIN country_capital cc ON co.code = cc.code""", nativeQuery = true)
    List<Country> findAllWithJoin();
}
