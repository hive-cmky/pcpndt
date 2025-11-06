//package in.gov.serviceplus.pcpndt.controllers;
//
//
//import in.gov.serviceplus.pcpndt.entity.Application;
//import in.gov.serviceplus.pcpndt.service.DataCollectionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class DataController {
//
//    @Autowired
//    private DataCollectionService dataService;
//
//    @GetMapping("/")
//    public String home(Model model){
//        model.addAttribute("stats", dataService.getStatistics());
//        return "home";
//    }
//
//    @GetMapping("/applications")
//    public String getAllApplications(Model model) {
//        List<Application> applications = dataService.getAllApplications();
//        System.out.println("Retrieved " + applications.size() + " applications");
//        model.addAttribute("applications", applications);
//        return "applications";
//    }
//
//    @GetMapping("/application/{id}")
//    public String getApplicationDetails(@PathVariable String id, Model model) {
//        Map<String, Object> applicationData = dataService.getCompleteApplicationDetails(id);
//        model.addAttribute("appData", applicationData);
//        return "application-details";
//    }
//
//    @GetMapping("/district/{district}")
//    public String getApplicationsByDistrict(@PathVariable String district, Model model) {
//        List<Application> applications = dataService.getApplicationsByDistrict(district);
//        model.addAttribute("applications", applications);
//        model.addAttribute("district", district);
//        return "applications-by-district";
//    }
//
//    @GetMapping("/type/{type}")
//    public String getApplicationsByType(@PathVariable String type, Model model) {
//        List<Application> applications = dataService.getApplicationsByType(type);
//        model.addAttribute("applications", applications);
//        model.addAttribute("type", type);
//        return "applications-by-type";
//    }
//
//    // Debug endpoint to check data
//    @GetMapping("/debug")
//    @ResponseBody
//    public String debug() {
//        List<Application> apps = dataService.getAllApplications();
//        return "Total applications: " + apps.size() + "<br>" + "Sample: " + (apps.isEmpty() ? "No data" : apps.get(0).getApplicantName());
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
