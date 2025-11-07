package darkchoco.reportsvc.service;

import darkchoco.reportsvc.entity.Customer;
import darkchoco.reportsvc.repository.CustomerRepository;
import darkchoco.reportsvc.util.CsvReportUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlatformReportService {

    private final CustomerRepository repository;

    Executor executor= Executors.newFixedThreadPool(5);

    public void generateReportForRegion(String region) {

        executor.execute(() -> {
            log.info("Platform generating report for region: {} | {}", region, Thread.currentThread());

            List<Customer> customers = repository.findByRegion(region);

            try {
                CsvReportUtil.writeCustomersToCsv("platform_" + region, customers);
            } catch (Exception e) {
                System.out.println("❌ Platform Error writing report for region: " + region);
            }
        });
    }
}
