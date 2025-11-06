////////package in.gov.serviceplus.pcpndt.controllers;
////////
////////
////////import in.gov.serviceplus.pcpndt.dto.FormHRecordDTO;
////////import in.gov.serviceplus.pcpndt.service.FormHService;
////////import org.slf4j.Logger;
////////import org.slf4j.LoggerFactory;
////////import org.springframework.beans.factory.annotation.Autowired;
////////import org.springframework.stereotype.Controller;
////////import org.springframework.ui.Model;
////////import org.springframework.web.bind.annotation.GetMapping;
////////import org.springframework.web.bind.annotation.PostMapping;
////////import org.springframework.web.bind.annotation.RequestParam;
////////
////////import java.util.ArrayList;
////////import java.util.List;
////////
////////@Controller
////////public class LandingController {
////////
////////    @Autowired
////////    private FormHService formHService;
////////
////////    private static final Logger logger = LoggerFactory.getLogger(LandingController.class);
////////
////////    @GetMapping("/")
////////    public String landingPage(Model model) {
////////        model.addAttribute("searchPerformed", false);
////////        return "landing";
////////    }
////////
////////    @PostMapping("/search")
////////    public String searchApplication(@RequestParam String referenceNumber, Model model) {
////////        model.addAttribute("searchPerformed", true);
////////        model.addAttribute("referenceNumber", referenceNumber);
////////
////////        try {
////////            FormHRecordDTO record = formHService.findByReferenceNumber(referenceNumber.trim());
////////
////////            if (record != null) {
////////                List<FormHRecordDTO> records = new ArrayList<>();
////////                records.add(record);
////////                model.addAttribute("records", records);
////////                model.addAttribute("found", true);
////////                model.addAttribute("pageNumber", 1);
////////                return "form-h-single";
////////            } else {
////////                model.addAttribute("found", false);
////////                model.addAttribute("message", "No application found with reference number: " + referenceNumber);
////////                return "landing";
////////            }
////////        } catch (Exception e) {
////////            logger.error("Error searching application {}: {}", referenceNumber, e.getMessage());
////////            model.addAttribute("found", false);
////////            model.addAttribute("message", "Error searching application: " + e.getMessage());
////////            return "landing";
////////        }
////////    }
////////}
//////
//////package in.gov.serviceplus.pcpndt.controllers;
//////
//////import in.gov.serviceplus.pcpndt.dto.FormHRecordDTO;
//////import in.gov.serviceplus.pcpndt.service.FormHService;
//////import org.slf4j.Logger;
//////import org.slf4j.LoggerFactory;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.http.HttpStatus;
//////import org.springframework.http.ResponseEntity;
//////import org.springframework.stereotype.Controller;
//////import org.springframework.ui.Model;
//////import org.springframework.web.bind.annotation.*;
//////
//////import java.util.HashMap;
//////import java.util.Map;
//////
//////@Controller
//////@RequestMapping("/api")
//////public class LandingController {
//////
//////    @Autowired
//////    private FormHService formHService;
//////
//////    private static final Logger logger = LoggerFactory.getLogger(LandingController.class);
//////
//////    @GetMapping("/")
//////    public String landingPage(Model model) {
//////        model.addAttribute("searchPerformed", false);
//////        return "landing";
//////    }
//////
//////    // JSON API endpoint for search
//////    @PostMapping("/search")
//////    @ResponseBody
//////    public ResponseEntity<Map<String, Object>> searchApplicationJson(@RequestParam String referenceNumber) {
//////        Map<String, Object> response = new HashMap<>();
//////
//////        try {
//////            logger.info("Searching for application with reference number: {}", referenceNumber);
//////
//////            FormHRecordDTO record = formHService.findByReferenceNumber(referenceNumber.trim());
//////
//////            if (record != null) {
//////                response.put("success", true);
//////                response.put("found", true);
//////                response.put("message", "Application found successfully");
//////                response.put("data", record);
//////                response.put("referenceNumber", referenceNumber);
//////
//////                logger.info("Application found: {}", record.getFileNumber());
//////                return ResponseEntity.ok(response);
//////            } else {
//////                response.put("success", true);
//////                response.put("found", false);
//////                response.put("message", "No application found with reference number: " + referenceNumber);
//////                response.put("data", null);
//////                response.put("referenceNumber", referenceNumber);
//////
//////                logger.warn("No application found for reference number: {}", referenceNumber);
//////                return ResponseEntity.ok(response);
//////            }
//////        } catch (Exception e) {
//////            logger.error("Error searching application {}: {}", referenceNumber, e.getMessage(), e);
//////
//////            response.put("success", false);
//////            response.put("found", false);
//////            response.put("message", "Error searching application: " + e.getMessage());
//////            response.put("error", e.getMessage());
//////            response.put("referenceNumber", referenceNumber);
//////
//////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//////        }
//////    }
//////
//////    // Alternative JSON endpoint using request body
//////    @PostMapping("/search/json")
//////    @ResponseBody
//////    public ResponseEntity<Map<String, Object>> searchApplicationJsonBody(@RequestBody Map<String, String> request) {
//////        String referenceNumber = request.get("referenceNumber");
//////
//////        if (referenceNumber == null || referenceNumber.trim().isEmpty()) {
//////            Map<String, Object> response = new HashMap<>();
//////            response.put("success", false);
//////            response.put("message", "Reference number is required");
//////            return ResponseEntity.badRequest().body(response);
//////        }
//////
//////        return searchApplicationJson(referenceNumber);
//////    }
//////
//////    // Keep the original Thymeleaf endpoint for backward compatibility
//////    @PostMapping("/search/html")
//////    public String searchApplicationHtml(@RequestParam String referenceNumber, Model model) {
//////        model.addAttribute("searchPerformed", true);
//////        model.addAttribute("referenceNumber", referenceNumber);
//////
//////        try {
//////            FormHRecordDTO record = formHService.findByReferenceNumber(referenceNumber.trim());
//////
//////            if (record != null) {
//////                model.addAttribute("record", record);
//////                model.addAttribute("found", true);
//////                return "form-h-single";
//////            } else {
//////                model.addAttribute("found", false);
//////                model.addAttribute("message", "No application found with reference number: " + referenceNumber);
//////                return "landing";
//////            }
//////        } catch (Exception e) {
//////            logger.error("Error searching application {}: {}", referenceNumber, e.getMessage(), e);
//////            model.addAttribute("found", false);
//////            model.addAttribute("message", "Error searching application: " + e.getMessage());
//////            return "landing";
//////        }
//////    }
//////}
////package in.gov.serviceplus.pcpndt.controllers;
////
////import in.gov.serviceplus.pcpndt.dto.FormHRecordDTO;
////import in.gov.serviceplus.pcpndt.service.FormHService;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.HashMap;
////import java.util.Map;
////
////@Controller
////@RequestMapping("/api")
////public class LandingController {
////
////    @Autowired
////    private FormHService formHService;
////
////    private static final Logger logger = LoggerFactory.getLogger(LandingController.class);
////
////    @GetMapping("/")
////    public String landingPage(Model model) {
////        model.addAttribute("searchPerformed", false);
////        return "landing";
////    }
////
////    // JSON API endpoint for search
////    @PostMapping("/search")
////    @ResponseBody
////    public ResponseEntity<Map<String, Object>> searchApplicationJson(@RequestParam String referenceNumber) {
////        Map<String, Object> response = new HashMap<>();
////
////        try {
////            logger.info("Searching for application with reference number: {}", referenceNumber);
////
////            // First test with simple query
////            System.out.println("=== TESTING WITH SIMPLE QUERY ===");
////            FormHRecordDTO simpleRecord = formHService.findByReferenceNumberSimple(referenceNumber.trim());
////
////            if (simpleRecord != null) {
////                System.out.println("Simple query SUCCESS - Record found");
////                // Now try the complex query
////                System.out.println("=== TESTING WITH COMPLEX QUERY ===");
////                FormHRecordDTO record = formHService.findByReferenceNumber(referenceNumber.trim());
////
////                response.put("success", true);
////                response.put("found", true);
////                response.put("message", "Application found successfully");
////                response.put("data", record);
////                response.put("referenceNumber", referenceNumber);
////
////                logger.info("Application found: {}", record.getFileNumber());
////                return ResponseEntity.ok(response);
////            } else {
////                response.put("success", true);
////                response.put("found", false);
////                response.put("message", "No application found with reference number: " + referenceNumber);
////                response.put("data", null);
////                response.put("referenceNumber", referenceNumber);
////
////                logger.warn("No application found for reference number: {}", referenceNumber);
////                return ResponseEntity.ok(response);
////            }
////
////        } catch (Exception e) {
////            logger.error("Error searching application {}: {}", referenceNumber, e.getMessage(), e);
////
////            response.put("success", false);
////            response.put("found", false);
////            response.put("message", "Error searching application: " + e.getMessage());
////            response.put("error", e.getMessage());
////            response.put("referenceNumber", referenceNumber);
////
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
////        }
////    }
////
////    // Alternative JSON endpoint using request body
////    @PostMapping("/search/json")
////    @ResponseBody
////    public ResponseEntity<Map<String, Object>> searchApplicationJsonBody(@RequestBody Map<String, String> request) {
////        String referenceNumber = request.get("referenceNumber");
////
////        if (referenceNumber == null || referenceNumber.trim().isEmpty()) {
////            Map<String, Object> response = new HashMap<>();
////            response.put("success", false);
////            response.put("message", "Reference number is required");
////            return ResponseEntity.badRequest().body(response);
////        }
////
////        return searchApplicationJson(referenceNumber);
////    }
////
////    // Keep the original Thymeleaf endpoint for backward compatibility
////    @PostMapping("/search/html")
////    public String searchApplicationHtml(@RequestParam String referenceNumber, Model model) {
////        model.addAttribute("searchPerformed", true);
////        model.addAttribute("referenceNumber", referenceNumber);
////
////        try {
////            FormHRecordDTO record = formHService.findByReferenceNumber(referenceNumber.trim());
////
////            if (record != null) {
////                model.addAttribute("record", record);
////                model.addAttribute("found", true);
////                return "form-h-single";
////            } else {
////                model.addAttribute("found", false);
////                model.addAttribute("message", "No application found with reference number: " + referenceNumber);
////                return "landing";
////            }
////        } catch (Exception e) {
////            logger.error("Error searching application {}: {}", referenceNumber, e.getMessage(), e);
////            model.addAttribute("found", false);
////            model.addAttribute("message", "Error searching application: " + e.getMessage());
////            return "landing";
////        }
////    }
////}
//
//package in.gov.serviceplus.pcpndt.controllers;
//
//import in.gov.serviceplus.pcpndt.dto.FormHRecordDTO;
//import in.gov.serviceplus.pcpndt.service.FormHService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/api")
//public class LandingController {
//
//    @Autowired
//    private FormHService formHService;
//
//    private static final Logger logger = LoggerFactory.getLogger(LandingController.class);
//
//    @GetMapping("/")
//    public String landingPage(Model model) {
//        model.addAttribute("searchPerformed", false);
//        return "landing";
//    }
//
//    // JSON API endpoint for search
//    @PostMapping("/search")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> searchApplicationJson(@RequestParam String referenceNumber) {
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//            logger.info("Searching for application with reference number: {}", referenceNumber);
//
//            // Use the main method directly (no simple test)
//            FormHRecordDTO record = formHService.findByReferenceNumber(referenceNumber.trim());
//
//            if (record != null) {
//                response.put("success", true);
//                response.put("found", true);
//                response.put("message", "Application found successfully");
//                response.put("data", record);
//                response.put("referenceNumber", referenceNumber);
//
//                logger.info("Application found: {}", record.getFileNumber());
//                return ResponseEntity.ok(response);
//            } else {
//                response.put("success", true);
//                response.put("found", false);
//                response.put("message", "No application found with reference number: " + referenceNumber);
//                response.put("data", null);
//                response.put("referenceNumber", referenceNumber);
//
//                logger.warn("No application found for reference number: {}", referenceNumber);
//                return ResponseEntity.ok(response);
//            }
//        } catch (Exception e) {
//            logger.error("Error searching application {}: {}", referenceNumber, e.getMessage(), e);
//
//            response.put("success", false);
//            response.put("found", false);
//            response.put("message", "Error searching application: " + e.getMessage());
//            response.put("error", e.getMessage());
//            response.put("referenceNumber", referenceNumber);
//
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
//
//    // Alternative JSON endpoint using request body
//    @PostMapping("/search/json")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> searchApplicationJsonBody(@RequestBody Map<String, String> request) {
//        String referenceNumber = request.get("referenceNumber");
//
//        if (referenceNumber == null || referenceNumber.trim().isEmpty()) {
//            Map<String, Object> response = new HashMap<>();
//            response.put("success", false);
//            response.put("message", "Reference number is required");
//            return ResponseEntity.badRequest().body(response);
//        }
//
//        return searchApplicationJson(referenceNumber);
//    }
//
//    // Keep the original Thymeleaf endpoint for backward compatibility
//    @PostMapping("/search/html")
//    public String searchApplicationHtml(@RequestParam String referenceNumber, Model model) {
//        model.addAttribute("searchPerformed", true);
//        model.addAttribute("referenceNumber", referenceNumber);
//
//        try {
//            FormHRecordDTO record = formHService.findByReferenceNumber(referenceNumber.trim());
//
//            if (record != null) {
//                model.addAttribute("record", record);
//                model.addAttribute("found", true);
//                return "form-h-single";
//            } else {
//                model.addAttribute("found", false);
//                model.addAttribute("message", "No application found with reference number: " + referenceNumber);
//                return "landing";
//            }
//        } catch (Exception e) {
//            logger.error("Error searching application {}: {}", referenceNumber, e.getMessage(), e);
//            model.addAttribute("found", false);
//            model.addAttribute("message", "Error searching application: " + e.getMessage());
//            return "landing";
//        }
//    }
//}
