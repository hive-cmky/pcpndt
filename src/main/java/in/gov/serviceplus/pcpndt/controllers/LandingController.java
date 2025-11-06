package in.gov.serviceplus.pcpndt.controllers;

import in.gov.serviceplus.pcpndt.dto.FormHRecordDTO;
import in.gov.serviceplus.pcpndt.service.FormHService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
// Remove the @RequestMapping("/api") since we want the main endpoints at root level
public class LandingController {

    @Autowired
    private FormHService formHService;

    private static final Logger logger = LoggerFactory.getLogger(LandingController.class);

    @GetMapping("/")
    public String landingPage(Model model) {
        model.addAttribute("searchPerformed", false);
        return "landing";
    }

//    // Thymeleaf endpoint for search
//    @PostMapping("/search")
//    public String searchApplication(@RequestParam String referenceNumber, Model model) {
//        model.addAttribute("searchPerformed", true);
//        model.addAttribute("referenceNumber", referenceNumber);
//
//        try {
//            FormHRecordDTO record = formHService.findByReferenceNumber(referenceNumber.trim());
//
//            if (record != null) {
//                model.addAttribute("record", record);
//                model.addAttribute("found", true);
//                return "form-H";
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

    // Thymeleaf endpoint for search
@PostMapping("/search")
public String searchApplication(@RequestParam String referenceNumber, Model model) {
    logger.info("Searching for application with reference number: {}", referenceNumber);

    model.addAttribute("searchPerformed", true);
    model.addAttribute("referenceNumber", referenceNumber);

    try {
        if (referenceNumber == null || referenceNumber.trim().isEmpty()) {
            model.addAttribute("found", false);
            model.addAttribute("message", "Please enter a reference number");
            return "landing";
        }

        FormHRecordDTO record = formHService.findByReferenceNumber(referenceNumber.trim());
        logger.info("FormHService returned record: {}", record);

        if (record != null) {
            logger.info("Application found, redirecting to form-H.html");

            // Add the record to model
            model.addAttribute("record", record);
            model.addAttribute("found", true);

            // Add additional attributes that form-H.html expects
            model.addAttribute("pageNumber", 1);
            model.addAttribute("slNo", 1);

            // Debug logging
            logger.debug("Record details - FileNumber: {}, Applicant: {}, Centre: {}",
                    record.getFileNumber(), record.getApplicantName(), record.getCentreName());

            return "form-H-test"; // This will render form-H.html with data
        } else {
            logger.info("No application found for reference: {}", referenceNumber);
            model.addAttribute("found", false);
            model.addAttribute("message", "No application found with reference number: " + referenceNumber);
            return "landing";
        }
    } catch (Exception e) {
        logger.error("Error searching application {}: {}", referenceNumber, e.getMessage(), e);
        model.addAttribute("found", false);
        model.addAttribute("message", "Error searching application: " + e.getMessage());
        return "landing";
    }
}







    // Keep JSON API endpoints for external use (optional)
    @PostMapping("/api/search")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> searchApplicationJson(@RequestParam String referenceNumber) {
        Map<String, Object> response = new HashMap<>();

        try {
            logger.info("Searching for application with reference number: {}", referenceNumber);

            FormHRecordDTO record = formHService.findByReferenceNumber(referenceNumber.trim());

            if (record != null) {
                response.put("success", true);
                response.put("found", true);
                response.put("message", "Application found successfully");
                response.put("data", record);
                response.put("referenceNumber", referenceNumber);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", true);
                response.put("found", false);
                response.put("message", "No application found with reference number: " + referenceNumber);
                response.put("data", null);
                response.put("referenceNumber", referenceNumber);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            logger.error("Error searching application {}: {}", referenceNumber, e.getMessage(), e);

            response.put("success", false);
            response.put("found", false);
            response.put("message", "Error searching application: " + e.getMessage());
            response.put("error", e.getMessage());
            response.put("referenceNumber", referenceNumber);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}