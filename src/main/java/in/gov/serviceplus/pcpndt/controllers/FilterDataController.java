package in.gov.serviceplus.pcpndt.controllers;

import in.gov.serviceplus.pcpndt.entity.ApplicationMaster;
import in.gov.serviceplus.pcpndt.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/filters")
@CrossOrigin(origins = "*")
public class FilterDataController {
    @Autowired
    private FilterService filterService;

    /**
     * Get all available services
     * GET /api/filters/services
     */
    @GetMapping("/services")
    public ResponseEntity<List<String>> getAllServices() {
        try {
            List<String> services = filterService.getAllServices();
            return ResponseEntity.ok(services);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get all unique districts extracted from submission_locations
     * GET /api/filters/districts
     */
    @GetMapping("/districts")
    public ResponseEntity<List<String>> getAllDistricts() {
        try {
            List<String> districts = filterService.getAllDistricts();
            return ResponseEntity.ok(districts);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get applications filtered by service and district
     * GET /api/filters/applications?service=PC+PNDT&district=MAYURBHANJ
     */
    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationMaster>> getApplicationsByFilter(
            @RequestParam String service,
            @RequestParam String district) {
        try {
            List<ApplicationMaster> applications = filterService.getApplicationsByServiceAndDistrict(service, district);
            return ResponseEntity.ok(applications);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Test endpoint to see raw submission locations and extracted districts
     * GET /api/filters/debug-districts
     */
//    @GetMapping("/debug-districts")
//    public ResponseEntity<Map<String, Object>> debugDistrictExtraction() {
//        try {
//            List<String> submissionLocations = filterService.getApplicationMasterRepository().findAllDistinctSubmissionLocations();
//
//            Map<String, String> extractionResults = new HashMap<>();
//            for (String location : submissionLocations) {
//                String extractedDistrict = filterService.extractDistrictFromSubmissionLocation(location);
//                extractionResults.put(location, extractedDistrict);
//            }
//
//            List<String> finalDistricts = filterService.getAllDistricts();
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("submissionLocations", submissionLocations);
//            response.put("extractionMapping", extractionResults);
//            response.put("finalDistricts", finalDistricts);
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Map<String, Object> error = new HashMap<>();
//            error.put("error", e.getMessage());
//            return ResponseEntity.internalServerError().body(error);
//        }
//    }
}
