package in.gov.serviceplus.pcpndt.controllers;


import in.gov.serviceplus.pcpndt.dto.response.FormHDataDto;
import in.gov.serviceplus.pcpndt.service.FormHDataService;
import in.gov.serviceplus.pcpndt.service.exception.ApplicationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/form-h")
public class FormHDataController {
    private final FormHDataService formHDataService;
    private int serialNumberCounter = 1; // Simple in-memory counter for serial numbers

    public FormHDataController(FormHDataService formHDataService) {
        this.formHDataService = formHDataService;
    }

    /**
     * HTML ENDPOINT: Show search form page
     * GET /form-h/search
     */
    @GetMapping("/search")
    public String showSearchForm(Model model) {
        // Add empty search result to model
        model.addAttribute("searchPerformed", false);
        model.addAttribute("error", null);
        return "form-h/search-form";
    }


    // HTML ENDPOINT: Process search and show Form H reportGET /form-h/report

    @GetMapping("/report")
    public String getFormHReport(@RequestParam String applRefNo, Model model) {
        try {
            // Get Form H data with auto-incremented serial number
            FormHDataDto formHData = formHDataService.getFormHData(applRefNo, serialNumberCounter++);

            // Add data to model for Thymeleaf template
            model.addAttribute("record", formHData);
            model.addAttribute("searchPerformed", true);
            model.addAttribute("error", null);
            model.addAttribute("pageNumber", 1);

            // Authority details (you can make these configurable)
            model.addAttribute("authorityName", "Dr. Medical Officer");
            model.addAttribute("authorityDesignation", "Chief District Medical Officer");

            return "form-h/form-h-report";

        } catch (ApplicationNotFoundException ex) {
            model.addAttribute("searchPerformed", true);
            model.addAttribute("error", "Application not found with reference number: " + applRefNo);
            model.addAttribute("record", null);
            return "form-h/search-form";
        } catch (Exception ex) {
            model.addAttribute("searchPerformed", true);
            model.addAttribute("error", "An error occurred while processing your request: " + ex.getMessage());
            model.addAttribute("record", null);
            return "form-h/search-form";
        }
    }


    // JSON API ENDPOINT: Get Form H data as JSON (for Postman/testing)
    // GET /form-h/api/data
    @GetMapping("/api/data")
    @ResponseBody
    public ResponseEntity<?> getFormHDataJson(@RequestParam String applRefNo) {
        try {
            FormHDataDto formHData = formHDataService.getFormHData(applRefNo, serialNumberCounter++);
            return ResponseEntity.ok(formHData);

        } catch (ApplicationNotFoundException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "APPLICATION_NOT_FOUND");
            errorResponse.put("message", ex.getMessage());
            errorResponse.put("applRefNo", applRefNo);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "INTERNAL_SERVER_ERROR");
            errorResponse.put("message", "An internal server error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    // JSON API ENDPOINT: Get Form H data with custom serial number
    // GET /form-h/api/data-with-serial

    @GetMapping("/api/data-with-serial")
    @ResponseBody
    public ResponseEntity<?> getFormHDataWithSerial(
            @RequestParam String applRefNo,
            @RequestParam(defaultValue = "1") Integer serialNumber) {
        try {
            FormHDataDto formHData = formHDataService.getFormHData(applRefNo, serialNumber);
            return ResponseEntity.ok(formHData);

        } catch (ApplicationNotFoundException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "APPLICATION_NOT_FOUND");
            errorResponse.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }


    // JSON API ENDPOINT: Health check for Form H API
    // GET /form-h/api/health
    @GetMapping("/api/health")
    @ResponseBody
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Form H Data API");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(response);
    }


    // HTML ENDPOINT: Reset serial number counter (for testing)
    // GET /form-h/admin/reset-counter
    @GetMapping("/admin/reset-counter")
    @ResponseBody
    public ResponseEntity<Map<String, String>> resetCounter() {
        serialNumberCounter = 1;
        Map<String, String> response = new HashMap<>();
        response.put("message", "Serial number counter reset to 1");
        response.put("newCounterValue", String.valueOf(serialNumberCounter));
        return ResponseEntity.ok(response);
    }

    // HTML ENDPOINT: Get current counter value (for testing)GET /form-h/admin/counter-value
    @GetMapping("/admin/counter-value")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getCounterValue() {
        Map<String, String> response = new HashMap<>();
        response.put("currentCounterValue", String.valueOf(serialNumberCounter));
        response.put("nextSerialNumber", String.valueOf(serialNumberCounter));
        return ResponseEntity.ok(response);
    }
}
