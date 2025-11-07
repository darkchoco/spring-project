package darkchoco.dp.service;

import org.springframework.stereotype.Component;

@Component
public class USATax implements TaxStrategy {

    @Override
    public double calculateTax(double amount) {
        return amount * 0.07;
    }

    @Override
    public String getCountryCode() {
        return "US";
    }
}
