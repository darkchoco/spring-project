package darkchoco.reportsvc.controller;

import darkchoco.reportsvc.service.PlatformReportService;
import darkchoco.reportsvc.service.ReportService;
import darkchoco.reportsvc.service.VirtualReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    private final PlatformReportService platformReportService;

    private final VirtualReportService virtualReportService;

    @PostMapping("/{region}")
    public ResponseEntity<String> generateReport(@PathVariable String region) {
        reportService.generateReportForRegion(region);
        return ResponseEntity.ok( "✅ report started for region: " + region);
    }

    @PostMapping("/platform/{region}")
    public ResponseEntity<String> generateReportPlatform(@PathVariable String region) {
        platformReportService.generateReportForRegion(region);
        return ResponseEntity.ok( "✅ Platform report started for region: " + region);
    }

    @PostMapping("/virtual/{region}")
    public ResponseEntity<String> generateReportVirtual(@PathVariable String region) {
        virtualReportService.generateReportForRegion(region);
        return ResponseEntity.ok( "✅ Virtual report started for region: " + region);
    }
}
