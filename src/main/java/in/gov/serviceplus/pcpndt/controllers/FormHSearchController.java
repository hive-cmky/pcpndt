package in.gov.serviceplus.pcpndt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormHSearchController {

    /**
     * Serve the form-h-search HTML page
     * URL: http://localhost:8080/form-h-search
     */
    @GetMapping("/form-h-search")
    public String showFormHSearchPage() {
        return "form-h-search"; // This should match your HTML file name without .html
    }
}