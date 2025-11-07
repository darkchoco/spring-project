package darkchoco.reportsvc.service;

import darkchoco.reportsvc.entity.Customer;
import darkchoco.reportsvc.repository.CustomerRepository;
import darkchoco.reportsvc.util.CsvReportUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReportService {

    private final CustomerRepository repository;

    // For 300 requests:
    // - Tomcat default thread is 200
    // - 200 requests processing, but
    // - 100 requests waiting in queue
    public void generateReportForRegion(String region) {
        log.info("generating report for region: {} | {}", region, Thread.currentThread());

        List<Customer> customers = repository.findByRegion(region);  //1

        try {
            CsvReportUtil.writeCustomersToCsv("simple_" + region, customers);//2
        } catch (Exception e) {
            System.out.println("❌ Error writing report for region: " + region);
        }
    }
}
