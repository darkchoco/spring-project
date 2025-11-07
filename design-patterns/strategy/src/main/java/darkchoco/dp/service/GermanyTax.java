package darkchoco.dp.service;

import org.springframework.stereotype.Component;

@Component
public class GermanyTax implements TaxStrategy {

    @Override
    public double calculateTax(double amount) {
        return amount * 0.19;
    }

    @Override
    public String getCountryCode() {
        return "DE";
    }
}
