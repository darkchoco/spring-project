package darkchoco.dp.service;

import org.springframework.stereotype.Component;

@Component
public class KoreaTax implements TaxStrategy {

    @Override
    public double calculateTax(double amount) {
        return amount * 0.1;
    }

    @Override
    public String getCountryCode() {
        return "KR";
    }
}
