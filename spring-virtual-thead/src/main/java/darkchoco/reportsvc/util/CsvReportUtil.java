package darkchoco.reportsvc.util;

import darkchoco.reportsvc.entity.Customer;
import org.apache.commons.csv.*;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class CsvReportUtil {

    public static void writeCustomersToCsv(String region, List<Customer> customers) throws IOException {
        Path path = Paths.get("reports", region + "_report.csv");
        Files.createDirectories(path.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(path);
             CSVPrinter csvPrinter = new CSVPrinter(writer,
                             CSVFormat.DEFAULT.builder().setHeader("ID", "Name", "Email", "Gender", "Region").get())) {
            for (Customer customer : customers) {
                csvPrinter.printRecord(
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getGender(),
                        customer.getRegion());
            }
        }
    }
}
