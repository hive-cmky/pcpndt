//package in.gov.serviceplus.pcpndt.controllers;
//
//import in.gov.serviceplus.pcpndt.service.CascadingDropdownService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/cascading")
//@CrossOrigin(origins = "*")
//public class CascadingDropdownController {
//
//    @Autowired
//    private CascadingDropdownService cascadingDropdownService;
//
//    /**
//     * Get initial dropdown data (services + districts)
//     * GET: http://localhost:8080/api/cascading/initial-data
//     */
//    @GetMapping("/initial-data")
//    public ResponseEntity<Map<String, Object>> getInitialData() {
//        try {
//            Map<String, Object> data = cascadingDropdownService.getInitialDropdownData();
//            return ResponseEntity.ok(data);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body(Map.of("error", "Failed to load initial data: " + e.getMessage()));
//        }
//    }
//
//    /**
//     * Get centres for selected district
//     * GET: http://localhost:8080/api/cascading/centres?district=ANUGUL
//     */
//    @GetMapping("/centres")
//    public ResponseEntity<Map<String, Object>> getCentresByDistrict(@RequestParam String district) {
//        try {
//            List<String> centres = cascadingDropdownService.getCentresByDistrict(district);
//            Map<String, Object> response = new HashMap<>();
//            response.put("district", district);
//            response.put("centres", centres);
//            response.put("count", centres.size());
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body(Map.of("error", "Failed to load centres: " + e.getMessage()));
//        }
//    }
//
//    /**
//     * Get application reference numbers for selected centre
//     * GET: http://localhost:8080/api/cascading/applications?centre=BISHNUPRIYA SEVA SADAN
//     */
//    @GetMapping("/applications")
//    public ResponseEntity<Map<String, Object>> getApplicationsByCentre(@RequestParam String centre) {
//        try {
//            List<String> applicationRefNos = cascadingDropdownService.getApplicationRefNosByCentre(centre);
//            Map<String, Object> response = new HashMap<>();
//            response.put("centre", centre);
//            response.put("applicationRefNos", applicationRefNos);
//            response.put("count", applicationRefNos.size());
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body(Map.of("error", "Failed to load applications: " + e.getMessage()));
//        }
//    }
//
//    /**
//     * Get applications with details for dropdown (enhanced)
//     * GET: http://localhost:8080/api/cascading/applications-with-details?centre=BISHNUPRIYA SEVA SADAN
//     */
//    @GetMapping("/applications-with-details")
//    public ResponseEntity<Map<String, Object>> getApplicationsWithDetails(@RequestParam String centre) {
//        try {
//            List<String> applicationRefNos = cascadingDropdownService.getApplicationRefNosByCentre(centre);
//
//            // Get additional details for each application
//            List<Map<String, String>> applicationOptions = new ArrayList<>();
//            for (String applRefNo : applicationRefNos) {
//                Map<String, String> appOption = new HashMap<>();
//                appOption.put("value", applRefNo);
//                appOption.put("displayText", applRefNo);
//                appOption.put("type", "Application");
//                applicationOptions.add(appOption);
//            }
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("centre", centre);
//            response.put("applicationOptions", applicationOptions);
//            response.put("count", applicationRefNos.size());
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body(Map.of("error", "Failed to load applications: " + e.getMessage()));
//        }
//    }
//
//    /**
//     * Get complete selection data for Form H generation
//     * GET: http://localhost:8080/api/cascading/application-details?applRefNo=PCPNDT/2025/00127
//     */
//    @GetMapping("/application-details")
//    public ResponseEntity<Map<String, Object>> getApplicationDetails(@RequestParam String applRefNo) {
//        try {
//            Map<String, Object> response = new HashMap<>();
//            response.put("applRefNo", applRefNo);
//            response.put("message", "Ready for Form H generation");
//            response.put("status", "SUCCESS");
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body(Map.of("error", "Failed to load application details: " + e.getMessage()));
//        }
//    }
//}