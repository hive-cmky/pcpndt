package in.gov.serviceplus.pcpndt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Controller
public class NewFormHController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/new-search")
    public String newSearchPage(Model model) {
        return "form-h/new-search-form";
    }

    @GetMapping("/new-search/results")
    public String newSearchResults(@RequestParam String applRefNo, Model model) {
        try {
            // FIX: URL encode the application reference number to handle special characters like /
            //  String encodedApplRefNo = URLEncoder.encode(applRefNo, StandardCharsets.UTF_8);
            //  String apiUrl = "http://localhost:8080/form-h/api/data?applRefNo=" + encodedApplRefNo;
            String apiUrl = "http://localhost:8080/form-h/api/data?applRefNo=" + applRefNo;

            // Make API call to get JSON data
            ResponseEntity<Map> response = restTemplate.getForEntity(apiUrl, Map.class);
            Map<String, Object> recordData = response.getBody();

            // Add the record data to model
            model.addAttribute("record", recordData);
            model.addAttribute("authorityName", "Dr. S. K. Sharma");
            model.addAttribute("authorityDesignation", "Chief District Medical Officer");
            model.addAttribute("pageNumber", 1);

            return "form-h/new-form-h-report";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Application not found: " + applRefNo);
            model.addAttribute("applRefNo", applRefNo);
            return "form-h/new-search-form";
        }
    }
}