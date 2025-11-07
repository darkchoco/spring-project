package darkchoco.dp.service;

public interface TaxStrategy {

    double calculateTax(double amount);
    String getCountryCode();
}
