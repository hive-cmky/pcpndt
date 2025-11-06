package in.gov.serviceplus.pcpndt.controllers;

import in.gov.serviceplus.pcpndt.service.CascadingDropdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*")
public class SearchDataController {

    @Autowired
    private CascadingDropdownService cascadingDropdownService;

    /**
     * Get initial search data (services + districts)
     * GET: http://localhost:8080/api/search/initial-data
     */
    @GetMapping("/initial-data")
    public ResponseEntity<Map<String, Object>> getInitialData() {
        try {
            Map<String, Object> data = cascadingDropdownService.getInitialDropdownData();
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to load initial data: " + e.getMessage()));
        }
    }

    /**
     * Get all districts for search
     * GET: http://localhost:8080/api/search/districts
     */
    @GetMapping("/districts")
    public ResponseEntity<Map<String, Object>> getAllDistricts() {
        try {
            List<String> districts = cascadingDropdownService.getAllDistricts();
            Map<String, Object> response = new HashMap<>();
            response.put("districts", districts);
            response.put("count", districts.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to load districts: " + e.getMessage()));
        }
    }

    /**
     * Get centres for selected district
     * GET: http://localhost:8080/api/search/centres?district=ANUGUL
     */
    @GetMapping("/centres")
    public ResponseEntity<Map<String, Object>> getCentresByDistrict(@RequestParam String district) {
        try {
            List<String> centres = cascadingDropdownService.getCentresByDistrict(district);
            Map<String, Object> response = new HashMap<>();
            response.put("district", district);
            response.put("centres", centres);
            response.put("count", centres.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to load centres: " + e.getMessage()));
        }
    }

    /**
     * Get application reference numbers for selected centre
     * GET: http://localhost:8080/api/search/applications?centre=BISHNUPRIYA SEVA SADAN
     */
    @GetMapping("/applications")
    public ResponseEntity<Map<String, Object>> getApplicationsByCentre(@RequestParam String centre) {
        try {
            List<String> applicationRefNos = cascadingDropdownService.getApplicationRefNosByCentre(centre);
            Map<String, Object> response = new HashMap<>();
            response.put("centre", centre);
            response.put("applicationRefNos", applicationRefNos);
            response.put("count", applicationRefNos.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to load applications: " + e.getMessage()));
        }
    }

    /**
     * Health check for search API
     * GET: http://localhost:8080/api/search/health
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "PCPNDT Search API",
                "message", "Search endpoints are working correctly"
        ));
    }
}