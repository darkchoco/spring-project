package darkchoco.spring.narasrest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@ToString
@Getter
@Entity
@Table(name = "country")
public class Country {

    @Id
    private String code;
    private String commonName;
    private String officialName;
    private String flagEmoji;
    private String flagImg;
    private String region;
    private int population;

    @JsonProperty("googleMapURL")
    private String googleMapUrl;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<CountryCapital> capitals;
}
