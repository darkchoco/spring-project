package darkchoco.spring.narasrest.controller;

import darkchoco.spring.narasrest.dto.CountryDTO;
import darkchoco.spring.narasrest.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = RequestMethod.GET)
@RestController
public class CountryApiController {

    private final CountryService countryService;

    public CountryApiController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries/{code}")
    public ResponseEntity<CountryDTO> findCountry(@PathVariable String code) {
        return ResponseEntity.ok()
                .body(countryService.findById(code.toUpperCase()));
    }

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDTO>> findCountries(@RequestParam(name = "q", required = false) String query) {
        if (query == null || query.isEmpty())
            return ResponseEntity.ok()
                    .body(countryService.findAll());

        return ResponseEntity.ok()
                .body(countryService.findByCommonNameContaining(query));
    }
}
