package com.edistrict.pcpndt.controller;


import com.edistrict.pcpndt.entity.PcpndtInitiatedData;
import com.edistrict.pcpndt.service.PcpndtInitiatedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pcpndt")
public class PcpndtWebController {

    @Autowired
    private PcpndtInitiatedDataService pcpndtInitiatedDataService;

    @GetMapping("/search")
    public String searchApplications(@RequestParam(required = false) String applRefNo, Model model) {
        if (applRefNo != null && !applRefNo.trim().isEmpty()) {
            List<PcpndtInitiatedData> applications = pcpndtInitiatedDataService.searchByApplRefNo(applRefNo);
            model.addAttribute("applications", applications);
            model.addAttribute("searchTerm", applRefNo);
            model.addAttribute("resultCount", applications.size());
        } else {
            model.addAttribute("applications", List.of());
            model.addAttribute("resultCount", 0);
        }

        return "pcpndt-search";
    }

    @GetMapping("/application/{applRefNo}")
    public String getApplicationByRefNo(@PathVariable String applRefNo, Model model) {
        Optional<PcpndtInitiatedData> application = pcpndtInitiatedDataService.findByApplRefNo(applRefNo);

        if (application.isPresent()) {
            model.addAttribute("application", application.get());
            model.addAttribute("found", true);
        } else {
            model.addAttribute("found", false);
            model.addAttribute("applRefNo", applRefNo);
        }

        return "pcpndt-details";
    }

    @GetMapping("/all")
    public String getAllApplications(Model model) {
        List<PcpndtInitiatedData> applications = pcpndtInitiatedDataService.getAllApplications();
        model.addAttribute("applications", applications);
        return "pcpndt-list";
    }
}