package in.gov.serviceplus.pcpndt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SinglePageController {
    @GetMapping("/single-page")
    public String singlePageForm() {
        return "form-h/single-page-form";
    }
}
