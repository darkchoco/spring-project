package darkchoco.reportsvc.service;

import darkchoco.reportsvc.entity.Customer;
import darkchoco.reportsvc.repository.CustomerRepository;
import darkchoco.reportsvc.util.CsvReportUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

@Slf4j
@RequiredArgsConstructor
@Service
public class VirtualReportService {

    private final CustomerRepository repository;

    private final Executor virtualThreadExecutor ;

    public void generateReportForRegion(String region) {
        virtualThreadExecutor.execute(() -> {
            log.info("Virtual generating report for region: {} | {}", region, Thread.currentThread());

            List<Customer> customers = repository.findByRegion(region);
            try {
                CsvReportUtil.writeCustomersToCsv("virtual_" + region, customers);
            } catch (Exception e) {
                System.out.println("❌ Virtual Error writing report for region: " + region);
            }
        });
    }
}
