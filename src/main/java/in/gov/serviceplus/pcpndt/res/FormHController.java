//package in.gov.serviceplus.pcpndt.controllers;
//
//import in.gov.serviceplus.pcpndt.FormHRecordRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class FormHController {
//
//    private final FormHRecordRepository recordRepository;
//
//    public FormHController(FormHRecordRepository recordRepository){
//        this.recordRepository = recordRepository;
//    }
//
//    @GetMapping("/")
//    public String showFormPage(Model model) {
//        // List<Record> records = recordRepository.findAll();
//        model.addAttribute("records", recordRepository.findAll());
//        model.addAttribute("pageNumber", 1); //set dynamically if needed
//        return "form-H"; // (form-H.html)
//    }
//
//}
