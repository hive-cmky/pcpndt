package in.gov.serviceplus.pcpndt.res;
//
//import in.gov.serviceplus.pcpndt.entity.Application;
//import in.gov.serviceplus.pcpndt.service.ApplicationService;
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
//public class ApplicationController {
//
//    @Autowired
//    private ApplicationService applicationService;
//
//    @GetMapping("/applications")
//    public String getAllApplications(Model model) {
//        List<Application> applications = applicationService.getAllApplications();
//        System.out.println("Retrieved " + applications.size() + " applications"); // Debug
//
//        model.addAttribute("applications", applications);
//        return "applications";
//    }
//
//    @GetMapping("/application/{id}")
//    public String getApplicationDetails(@PathVariable Long id, Model model) {
//        Map<String, Object> details = applicationService.getApplicationDetails(id);
//
//        if (details.get("application") == null) {
//            model.addAttribute("error", "Application not found");
//            return "error";
//        }
//
//        model.addAttribute("details", details);
//        return "application-details";
//    }
//
//    // Debug endpoint to check data
//    @GetMapping("/debug/applications")
//    @ResponseBody
//    public String debugApplications() {
//        List<Application> apps = applicationService.getAllApplications();
//        StringBuilder result = new StringBuilder();
//        result.append("Total applications: ").append(apps.size()).append("<br><br>");
//
//        for (Application app : apps) {
//            result.append("ID: ").append(app.getApplicationId())
//                    .append(" | Name: ").append(app.getApplicantName())
//                    .append(" | Centre: ").append(app.getNameOfCentre())
//                    .append(" | Type: ").append(app.getApplicationType())
//                    .append("<br>");
//        }
//
//        return result.toString();
//    }
//}

//import in.gov.serviceplus.pcpndt.entity.PcpndtInitiatedData;
//import in.gov.serviceplus.pcpndt.service.ApplicationService;
//import in.gov.serviceplus.pcpndt.service.DatabaseCheckService;
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
//public class ApplicationController {
//
//    @Autowired
//    private ApplicationService applicationService;
//
//    @Autowired
//    private DatabaseCheckService databaseCheckService;
//
//    @GetMapping("/applications")
//    public String getAllApplications(Model model) {
//        databaseCheckService.checkDatabase();
//
//        List<PcpndtInitiatedData> applications = applicationService.getAllApplications();
//        System.out.println("Retrieved " + applications.size() + " applications");
//
//        model.addAttribute("applications", applications);
//        return "applications";
//    }
//
//    @GetMapping("/application/{id}")
//    public String getApplicationDetails(@PathVariable Long id, Model model) {
//        Map<String, Object> details = applicationService.getApplicationDetails(id);
//
//        if (details.get("application") == null) {
//            model.addAttribute("error", "Application not found");
//            return "error";
//        }
//
//        model.addAttribute("details", details);
//        return "application-details";
//    }
//
//    @GetMapping("/debug/database")
//    @ResponseBody
//    public String debugDatabase() {
//        databaseCheckService.checkDatabase();
//        return "Database check completed - check console logs";
//    }
//
//    @GetMapping("/debug/applications")
//    @ResponseBody
//    public String debugApplications() {
//        List<PcpndtInitiatedData> apps = applicationService.getAllApplications();
//        StringBuilder result = new StringBuilder();
//        result.append("Total applications: ").append(apps.size()).append("<br><br>");
//
//        for (PcpndtInitiatedData app : apps) {
//            result.append("ID: ").append(app.getAppl_id())
//                    .append(" | Name: ").append(app.getApplicant_name())
//                    .append(" | Centre: ").append(app.getName_of_centre())
//                    .append(" | Type: ").append(app.getApplication_type())
//                    .append("<br>");
//        }
//
//        return result.toString();
//    }
//}
