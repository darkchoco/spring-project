package darkchoco.dp.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TaxService {

    private final Map<String, TaxStrategy> strategyMap;

    // Spring이 모든 TaxStrategy 구현체를 List로 주입
    public TaxService(List<TaxStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(TaxStrategy::getCountryCode, Function.identity()));
    }

    public double getTax(String countryCode, double amount) {
        TaxStrategy strategy = strategyMap.get(countryCode);

        if (strategy == null)
            throw new IllegalArgumentException("Invalid country code: " + countryCode);

        // 전략에 계산을 위임
        return strategy.calculateTax(amount);
    }
}
