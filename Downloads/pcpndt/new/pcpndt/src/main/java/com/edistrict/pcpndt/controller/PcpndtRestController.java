package com.edistrict.pcpndt.controller;

import com.edistrict.pcpndt.entity.PcpndtInitiatedData;
import com.edistrict.pcpndt.service.PcpndtInitiatedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pcpndt")
@CrossOrigin(origins = "*")
public class PcpndtRestController {

    @Autowired
    private PcpndtInitiatedDataService pcpndtInitiatedDataService;

    @GetMapping("/application/{applRefNo}")
    public ResponseEntity<?> getApplicationByRefNo(@PathVariable String applRefNo) {
        Optional<PcpndtInitiatedData> application = pcpndtInitiatedDataService.findByApplRefNo(applRefNo);

        if (application.isPresent()) {
            return ResponseEntity.ok(application.get());
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Application not found with reference number: " + applRefNo);
            response.put("status", "404");
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchApplications(@RequestParam String searchTerm) {
        List<PcpndtInitiatedData> applications = pcpndtInitiatedDataService.comprehensiveSearch(searchTerm);

        Map<String, Object> response = new HashMap<>();
        response.put("count", applications.size());
        response.put("searchTerm", searchTerm);
        response.put("applications", applications);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/applicant")
    public ResponseEntity<?> searchByApplicantName(@RequestParam String applicantName) {
        List<PcpndtInitiatedData> applications = pcpndtInitiatedDataService.findByApplicantNameContaining(applicantName);

        Map<String, Object> response = new HashMap<>();
        response.put("count", applications.size());
        response.put("applicantName", applicantName);
        response.put("applications", applications);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PcpndtInitiatedData>> getAllApplications() {
        List<PcpndtInitiatedData> applications = pcpndtInitiatedDataService.getAllApplications();
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getApplicationStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalApplications", pcpndtInitiatedDataService.getTotalApplications());
        return ResponseEntity.ok(stats);
    }
}