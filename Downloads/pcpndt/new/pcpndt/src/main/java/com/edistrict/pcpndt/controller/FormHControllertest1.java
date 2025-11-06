//package com.edistrict.pcpndt.controller;
//
//import com.edistrict.pcpndt.dto.ApiResponseDTO;
//import com.edistrict.pcpndt.dto.FormHDataDTO;
//import com.edistrict.pcpndt.service.FormHService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/pcpndt")
//public class FormHController {
//    private final FormHService formHService;
//
//    // Add to FormHControllertest1.java
//    @GetMapping("/db/test")
//    @ResponseBody
//    public ResponseEntity<ApiResponseDTO<String>> testDatabase() {
//        log.info("Testing database connection...");
//
//        try {
//            List<String> refNos = formHService.getAllApplicationRefNos();
//            return ResponseEntity.ok(ApiResponseDTO.success(
//                    "Database connected! Found " + refNos.size() + " applications"
//            ));
//        } catch (Exception e) {
//            return ResponseEntity.ok(ApiResponseDTO.error(
//                    "Database error: " + e.getMessage()
//            ));
//        }
//    }
//
//    // Add this method to your FormHControllertest1.java
//    @GetMapping("/formh/test/{applRefNo}")
//    @ResponseBody
//    public ResponseEntity<ApiResponseDTO<String>> testFormHEndpoint(
//            @PathVariable String applRefNo) {
//        log.info("Testing Form H endpoint for: {}", applRefNo);
//
//        // Test if service is being called
//        Optional<FormHDataDTO> formHData = formHService.getFormHDataByApplRefNo(applRefNo);
//
//        if (formHData.isPresent()) {
//            return ResponseEntity.ok(ApiResponseDTO.success("Data found for: " + applRefNo));
//        } else {
//            return ResponseEntity.ok(ApiResponseDTO.success("No data found for: " + applRefNo));
//        }
//    }
//
//    // Add this to FormHControllertest1.java for debugging
//    @GetMapping("/debug/mappings")
//    @ResponseBody
//    public ResponseEntity<ApiResponseDTO<Map<String, Object>>> debugMappings() {
//        log.info("🔍 Debugging controller mappings");
//
//        Map<String, Object> debugInfo = new HashMap<>();
//        debugInfo.put("message", "Controller is working");
//        debugInfo.put("timestamp", new Date());
//
//        return ResponseEntity.ok(ApiResponseDTO.success(debugInfo));
//    }
//
//    // REST API endpoint for Postman/JSON output
//    @GetMapping("/formh/json/{applRefNo}")
//    @ResponseBody
//    public ResponseEntity<ApiResponseDTO<FormHDataDTO>> getFormHDataJson(
//            @PathVariable String applRefNo) {
//        log.info("REST API request for Form H data: {}", applRefNo);
//
//        Optional<FormHDataDTO> formHData = formHService.getFormHDataByApplRefNo(applRefNo);
//
//        if (formHData.isPresent()) {
//            return ResponseEntity.ok(ApiResponseDTO.success(formHData.get()));
//        } else {
//            return ResponseEntity.ok(ApiResponseDTO.error(
//                    "No Form H data found for application reference: " + applRefNo
//            ));
//        }
//    }
//
//    // Thymeleaf HTML endpoint
//    @GetMapping("/formh/html/{applRefNo}")
//    public String getFormHDataHtml(
//            @PathVariable String applRefNo,
//            Model model) {
//        log.info("Thymeleaf request for Form H data: {}", applRefNo);
//
//        Optional<FormHDataDTO> formHData = formHService.getFormHDataByApplRefNo(applRefNo);
//
//        if (formHData.isPresent()) {
//            model.addAttribute("record", formHData.get());
//            model.addAttribute("pageNumber", 1);
//            model.addAttribute("authorityName", "Chief District Medical Officer");
//            model.addAttribute("authorityDesignation", "Appropriate Authority");
//            return "form-h-template";
//        } else {
//            model.addAttribute("error", "No Form H data found for application reference: " + applRefNo);
//            return "error";
//        }
//    }
//
//    // HTMX compatible endpoint
//    @GetMapping("/formh/htmx/{applRefNo}")
//    public String getFormHDataHtmx(
//            @PathVariable String applRefNo,
//            Model model) {
//        log.info("HTMX request for Form H data: {}", applRefNo);
//
//        Optional<FormHDataDTO> formHData = formHService.getFormHDataByApplRefNo(applRefNo);
//
//        if (formHData.isPresent()) {
//            model.addAttribute("record", formHData.get());
//            model.addAttribute("pageNumber", 1);
//            model.addAttribute("authorityName", "Chief District Medical Officer");
//            model.addAttribute("authorityDesignation", "Appropriate Authority");
//            return "fragments/form-h-fragment :: formHContent";
//        } else {
//            model.addAttribute("error", "No Form H data found for: " + applRefNo);
//            return "fragments/error-fragment :: errorContent";
//        }
//    }
//
//    // Search page with HTMX
//    @GetMapping("/search")
//    public String showSearchPage(Model model) {
//        model.addAttribute("allRefNos", formHService.getAllApplicationRefNos());
//        return "search-page";
//    }
//
//    // Health check endpoint
//    @GetMapping("/health")
//    @ResponseBody
//    public ResponseEntity<ApiResponseDTO<String>> healthCheck() {
//        return ResponseEntity.ok(ApiResponseDTO.success("PCPNDT Form H API is running"));
//    }
//
//    @GetMapping("/service/test")
//    public ResponseEntity<ApiResponseDTO<String>> testService() {
//        log.info("🧪 Testing service layer directly...");
//
//        try {
//            // Get first available application reference for testing
//            List<String> allRefNos = formHService.getAllApplicationRefNos();
//            if (allRefNos.isEmpty()) {
//                return ResponseEntity.ok(ApiResponseDTO.error("No applications found in database"));
//            }
//
//            String testAppRefNo = allRefNos.get(0);
//            log.info("🧪 Testing with application: {}", testAppRefNo);
//
//            Optional<FormHDataDTO> result = formHService.getFormHDataByApplRefNo(testAppRefNo);
//
//            if (result.isPresent()) {
//                return ResponseEntity.ok(ApiResponseDTO.success(
//                        "✅ Service working! Found: " + result.get().getApplicantName() +
//                                " at " + result.get().getFacilityName()
//                ));
//            } else {
//                return ResponseEntity.ok(ApiResponseDTO.success(
//                        "⚠️ Service working but no detailed data found for: " + testAppRefNo
//                ));
//            }
//        } catch (Exception e) {
//            return ResponseEntity.ok(ApiResponseDTO.error(
//                    "❌ Service error: " + e.getMessage()
//            ));
//        }
//    }
//
//}
