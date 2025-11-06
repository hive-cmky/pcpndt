package com.edistrict.pcpndt.controller;

import com.edistrict.pcpndt.dto.ApiResponseDTO;
import com.edistrict.pcpndt.dto.FormHDataDTO;
import com.edistrict.pcpndt.service.FormHService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pcpndt")
public class FormHController {
    private final FormHService formHService;

    // ==================== TEST & DEBUG ENDPOINTS ====================

    @GetMapping("/health")
    public ResponseEntity<ApiResponseDTO<String>> healthCheck() {
        return ResponseEntity.ok(ApiResponseDTO.success("PCPNDT Form H API is running"));
    }

    @GetMapping("/db/test")
    public ResponseEntity<ApiResponseDTO<String>> testDatabase() {
        log.info("Testing database connection...");

        try {
            List<String> refNos = formHService.getAllApplicationRefNos();
            return ResponseEntity.ok(ApiResponseDTO.success(
                    "Database connected! Found " + refNos.size() + " applications"
            ));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponseDTO.error(
                    "Database error: " + e.getMessage()
            ));
        }
    }

    @GetMapping("/service/test")
    public ResponseEntity<ApiResponseDTO<String>> testService() {
        log.info("🧪 Testing service layer directly...");

        try {
            // Get first available application reference for testing
            List<String> allRefNos = formHService.getAllApplicationRefNos();
            if (allRefNos.isEmpty()) {
                return ResponseEntity.ok(ApiResponseDTO.error("No applications found in database"));
            }

            String testAppRefNo = allRefNos.get(0);
            log.info("🧪 Testing with application: {}", testAppRefNo);

            Optional<FormHDataDTO> result = formHService.getFormHDataByApplRefNo(testAppRefNo);

            if (result.isPresent()) {
                return ResponseEntity.ok(ApiResponseDTO.success(
                        "✅ Service working! Found: " + result.get().getApplicantName() +
                                " at " + result.get().getFacilityName()
                ));
            } else {
                return ResponseEntity.ok(ApiResponseDTO.success(
                        "⚠️ Service working but no detailed data found for: " + testAppRefNo
                ));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponseDTO.error(
                    "❌ Service error: " + e.getMessage()
            ));
        }
    }

    @GetMapping("/debug/mappings")
    public ResponseEntity<ApiResponseDTO<Map<String, Object>>> debugMappings() {
        log.info("🔍 Debugging controller mappings");

        Map<String, Object> debugInfo = new HashMap<>();
        debugInfo.put("message", "Controller is working");
        debugInfo.put("timestamp", new Date());

        return ResponseEntity.ok(ApiResponseDTO.success(debugInfo));
    }

    // ==================== FORM H API ENDPOINTS ====================

    @GetMapping("/formh/test/{applRefNo}")
    public ResponseEntity<ApiResponseDTO<String>> testFormHEndpoint(
            @PathVariable String applRefNo) {
        log.info("Testing Form H endpoint for: {}", applRefNo);

        // Test if service is being called
        Optional<FormHDataDTO> formHData = formHService.getFormHDataByApplRefNo(applRefNo);

        if (formHData.isPresent()) {
            return ResponseEntity.ok(ApiResponseDTO.success("Data found for: " + applRefNo));
        } else {
            return ResponseEntity.ok(ApiResponseDTO.success("No data found for: " + applRefNo));
        }
    }

    @GetMapping("/formh/json/{applRefNo}")
    public ResponseEntity<ApiResponseDTO<FormHDataDTO>> getFormHDataJson(
            @PathVariable String applRefNo) {
        log.info("REST API request for Form H data: {}", applRefNo);

        Optional<FormHDataDTO> formHData = formHService.getFormHDataByApplRefNo(applRefNo);

        if (formHData.isPresent()) {
            return ResponseEntity.ok(ApiResponseDTO.success(formHData.get()));
        } else {
            return ResponseEntity.ok(ApiResponseDTO.error(
                    "No Form H data found for application reference: " + applRefNo
            ));
        }
    }

    // ==================== THYMELEAF HTML ENDPOINTS ====================

    @GetMapping("/formh/html/{applRefNo}")
    public String getFormHDataHtml(
            @PathVariable String applRefNo,
            Model model) {
        log.info("Thymeleaf request for Form H data: {}", applRefNo);

        Optional<FormHDataDTO> formHData = formHService.getFormHDataByApplRefNo(applRefNo);

        if (formHData.isPresent()) {
            model.addAttribute("record", formHData.get());
            model.addAttribute("pageNumber", 1);
            model.addAttribute("authorityName", "Chief District Medical Officer");
            model.addAttribute("authorityDesignation", "Appropriate Authority");
            return "form-h-template";
        } else {
            model.addAttribute("error", "No Form H data found for application reference: " + applRefNo);
            return "error";
        }
    }

    @GetMapping("/formh/htmx/{applRefNo}")
    public String getFormHDataHtmx(
            @PathVariable String applRefNo,
            Model model) {
        log.info("HTMX request for Form H data: {}", applRefNo);

        Optional<FormHDataDTO> formHData = formHService.getFormHDataByApplRefNo(applRefNo);

        if (formHData.isPresent()) {
            model.addAttribute("record", formHData.get());
            model.addAttribute("pageNumber", 1);
            model.addAttribute("authorityName", "Chief District Medical Officer");
            model.addAttribute("authorityDesignation", "Appropriate Authority");
            return "fragments/form-h-fragment :: formHContent";
        } else {
            model.addAttribute("error", "No Form H data found for: " + applRefNo);
            return "fragments/error-fragment :: errorContent";
        }
    }

    @GetMapping("/service/test/all")
    public ResponseEntity<ApiResponseDTO<String>> testServiceWithAllRefs() {
        log.info("🧪 Testing service layer with ALL applications...");

        try {
            List<String> allRefNos = formHService.getAllApplicationRefNos();

            if (allRefNos.isEmpty()) {
                return ResponseEntity.ok(ApiResponseDTO.error("No applications found in database"));
            }

            StringBuilder result = new StringBuilder();
            result.append("Found ").append(allRefNos.size()).append(" applications:\n");

            // Test first 3 applications or all if less than 3
            int testCount = Math.min(3, allRefNos.size());

            for (int i = 0; i < testCount; i++) {
                String appRefNo = allRefNos.get(i);
                Optional<FormHDataDTO> data = formHService.getFormHDataByApplRefNo(appRefNo);

                result.append("\n").append(i + 1).append(". ").append(appRefNo).append(": ");
                if (data.isPresent()) {
                    result.append("✅ ").append(data.get().getApplicantName());
                } else {
                    result.append("❌ No detailed data");
                }
            }

            return ResponseEntity.ok(ApiResponseDTO.success(result.toString()));

        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponseDTO.error("❌ Service error: " + e.getMessage()));
        }
    }

    @GetMapping("/search")
    public String showSearchPage(Model model) {
        model.addAttribute("allRefNos", formHService.getAllApplicationRefNos());
        return "search-page";
    }

    @GetMapping("/formh/detailed/{applRefNo}")
    public ResponseEntity<ApiResponseDTO<Map<String, Object>>> getDetailedFormHData(
            @PathVariable String applRefNo) {
        log.info("📋 Detailed API request for application: {}", applRefNo);

        try {
            // Get basic form data
            Optional<FormHDataDTO> formHData = formHService.getFormHDataByApplRefNo(applRefNo);

            if (formHData.isEmpty()) {
                return ResponseEntity.ok(ApiResponseDTO.error(
                        "No data found for application reference: " + applRefNo
                ));
            }

            // Create comprehensive response
            Map<String, Object> detailedResponse = new HashMap<>();
            detailedResponse.put("basicInfo", formHData.get());
            detailedResponse.put("applicationRef", applRefNo);
            detailedResponse.put("timestamp", new Date());
            detailedResponse.put("dataSources", Arrays.asList(
                    "pcpndt_initiated_data",
                    "pcpndt_execution_data",
                    "pcpndt_filtered_execution"
            ));

            // Add specific info for PCPNDT/2021/00105
            if ("PCPNDT/2021/00105".equals(applRefNo)) {
                Map<String, Object> specificInfo = new HashMap<>();
                specificInfo.put("applicant", "RAGHAB PRASAD PANDA");
                specificInfo.put("facility", "SANTAAN FERTILITY CENTER AND RESEARCH INSTITUTE");
                specificInfo.put("district", "KHORDHA");
                specificInfo.put("applicationType", "Renewal");
                specificInfo.put("paymentAmount", "12500");
                specificInfo.put("status", "Active");

                detailedResponse.put("specificApplicationInfo", specificInfo);
            }

            return ResponseEntity.ok(ApiResponseDTO.success(detailedResponse));

        } catch (Exception e) {
            log.error("💥 Error in detailed endpoint for {}: {}", applRefNo, e.getMessage());
            return ResponseEntity.ok(ApiResponseDTO.error(
                    "Error processing request for: " + applRefNo
            ));
        }
    }

//    @GetMapping("/test/PCPNDT/2021/00105")
//    public ResponseEntity<ApiResponseDTO<String>> testSpecificApplication() {
//        log.info("🧪 Testing specific application: PCPNDT/2021/00105");
//
//        String applRefNo = "PCPNDT/2021/00105";
//
//        try {
//            // Check if application exists
//            List<String> allRefNos = formHService.getAllApplicationRefNos();
//            boolean exists = allRefNos.contains(applRefNo);
//
//            if (!exists) {
//                return ResponseEntity.ok(ApiResponseDTO.error(
//                        "Application " + applRefNo + " not found in database. Available: " + allRefNos
//                ));
//            }
//
//            // Try to get detailed data
//            Optional<FormHDataDTO> data = formHService.getFormHDataByApplRefNo(applRefNo);
//
//            if (data.isPresent()) {
//                FormHDataDTO dto = data.get();
//                String result = String.format(
//                        "✅ Found: %s at %s (Phone: %s, Email: %s, Type: %s)",
//                        dto.getApplicantName(),
//                        dto.getFacilityName(),
//                        dto.getPhoneNumber(),
//                        dto.getEmail(),
//                        dto.getApplicationType()
//                );
//                return ResponseEntity.ok(ApiResponseDTO.success(result));
//            } else {
//                return ResponseEntity.ok(ApiResponseDTO.success(
//                        "⚠️ Application exists but no detailed data found for: " + applRefNo
//                ));
//            }
//
//        } catch (Exception e) {
//            return ResponseEntity.ok(ApiResponseDTO.error(
//                    "❌ Error testing " + applRefNo + ": " + e.getMessage()
//            ));
//        }
//    }
}