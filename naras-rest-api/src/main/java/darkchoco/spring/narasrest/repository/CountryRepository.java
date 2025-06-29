package darkchoco.spring.narasrest.repository;

import darkchoco.spring.narasrest.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByCommonNameContainingIgnoreCase(String commonName);

//    @Query(value = """
//            SELECT cc.capital, co.code, co.common_name, co.official_name, co.flag_emoji, co.flag_img, co.region, co.population
//            FROM country co
//            INNER JOIN country_capital cc ON co.code = cc.code""", nativeQuery = true)
//    List<Country> findAllWithJoin();
    @SuppressWarnings("NullableProblems")
    @EntityGraph(attributePaths = {"capitals"})
    List<Country> findAll();
}
