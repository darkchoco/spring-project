package darkchoco.dp.controller;

import darkchoco.dp.service.TaxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final TaxService taxService;

    public InvoiceController(TaxService taxService) {
        this.taxService = taxService;
    }

    @GetMapping("/tax")
    public ResponseEntity<String> getTax(@RequestParam String country, @RequestParam double amount) {
        try {
            double tax = taxService.getTax(country, amount);
            return ResponseEntity.ok("Country: " + country.toUpperCase() + ", Amount: " + amount + ", Tax: " + tax);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
