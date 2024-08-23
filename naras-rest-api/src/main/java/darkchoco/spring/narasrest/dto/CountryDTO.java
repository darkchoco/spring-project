package darkchoco.spring.narasrest.dto;

import darkchoco.spring.narasrest.domain.Country;
import darkchoco.spring.narasrest.domain.CountryCapital;
import lombok.Getter;

import java.util.List;

@Getter
public class CountryDTO {

    private final String code;
    private final String commonName;
    private final String officialName;
    private final String flagEmoji;
    private final String flagImg;
    private final List<String> capital;
    private final String region;
    private final int population;
    private final String googleMapUrl;

    public CountryDTO(Country country) {
        this.code = country.getCode();
        this.commonName = country.getCommonName();
        this.officialName = country.getOfficialName();
        this.flagEmoji = country.getFlagEmoji();
        this.flagImg = country.getFlagImg();
        this.capital = country.getCapital()
                .stream()
                .map(CountryCapital::getCapital)
                .toList();
        this.region = country.getRegion();
        this.population = country.getPopulation();
        this.googleMapUrl = country.getGoogleMapUrl();
    }
}
