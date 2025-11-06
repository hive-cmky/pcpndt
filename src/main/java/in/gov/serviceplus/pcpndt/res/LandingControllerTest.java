//package in.gov.serviceplus.pcpndt.controllers;
//
//import in.gov.serviceplus.pcpndt.dto.FormHRecordDTO;
//import in.gov.serviceplus.pcpndt.service.FormHService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class LandingController {
//
//    @Autowired
//    private FormHService formHService;
//
//    private static final Logger logger = LoggerFactory.getLogger(LandingController.class);
//
//    @PostMapping("/search")
//    public String searchApplication(@RequestParam String referenceNumber, Model model) {
//        logger.info("Searching for application with reference: {}", referenceNumber);
//
//        model.addAttribute("searchPerformed", true);
//        model.addAttribute("referenceNumber", referenceNumber);
//
//        try {
//            // Search for Form-H record by reference number
//            FormHRecordDTO record = formHService.findByReferenceNumber(referenceNumber.trim());
//
//            if (record != null) {
//                logger.info("Application found: {}", referenceNumber);
//                List<FormHRecordDTO> records = new ArrayList<>();
//                records.add(record);
//                model.addAttribute("records", records);
//                model.addAttribute("found", true);
//                model.addAttribute("pageNumber", 1);
//                return "form-h-single";
//            } else {
//                logger.info("No application found: {}", referenceNumber);
//                model.addAttribute("found", false);
//                model.addAttribute("message",
//                        "No application found with reference number: " + referenceNumber);
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