//package in.gov.serviceplus.pcpndt.service;
//
//import in.gov.serviceplus.pcpndt.entity.Application;
//import in.gov.serviceplus.pcpndt.entity.ExecutionHistory;
//import in.gov.serviceplus.pcpndt.entity.FilteredExecution;
//import in.gov.serviceplus.pcpndt.repository.ApplicationRepository;
//import in.gov.serviceplus.pcpndt.repository.ExecutionHistoryRepository;
//import in.gov.serviceplus.pcpndt.repository.FilteredExecutionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class DataCollectionService {
//
//    @Autowired
//    private ApplicationRepository applicationRepository;
//
//    @Autowired
//    private ExecutionHistoryRepository executionHistoryRepository;
//
//    @Autowired
//    private FilteredExecutionRepository filteredExecutionRepository;
//
//    // Get all applications
//    public List<Application> getAllApplications() {
//        return applicationRepository.findAll();
//    }
//
//    // Get application by ID
//    public Application getApplicationById(String applicationId) {
//        return applicationRepository.findById(applicationId).orElse(null);
//    }
//
//    // Get execution history for an application
//    public List<ExecutionHistory> getExecutionHistory(String applicationId) {
//        return executionHistoryRepository.findByApplicationId(applicationId);
//    }
//
//    // Get filtered execution for an application
//    public List<FilteredExecution> getFilteredExecution(String applicationId) {
//        return filteredExecutionRepository.findByApplicationId(applicationId);
//    }
//
//    // Get applications by district
//    public List<Application> getApplicationsByDistrict(String district) {
//        return applicationRepository.findByDistrictContainingIgnoreCase(district);
//    }
//
//    // Get applications by type
//    public List<Application> getApplicationsByType(String applicationType) {
//        return applicationRepository.findByApplicationTypeContainingIgnoreCase(applicationType);
//    }
//
//    //Get complete application details with execution history
//    public Map<String, Object> getCompleteApplicationDetails(String applicationId) {
//        Map<String, Object> result = new HashMap<>();
//        Application application = getApplicationById(applicationId);
//        List<ExecutionHistory> executionHistory = getExecutionHistory(applicationId);
//        List<FilteredExecution> filteredExecution = getFilteredExecution(applicationId);
//
//        result.put("application", application);
//        result.put("executionHistory", executionHistory);
//        result.put("filteredExecution", filteredExecution);
//        return result;
//    }
//
//    // Get statistics
//    public Map<String, Long> getStatistics() {
//        Map<String, Long> stats = new HashMap<>();
//        stats.put("totalApplications", applicationRepository.count());
//        stats.put("totalExecutionRecords", executionHistoryRepository.count());
//        stats.put("totalFilteredRecords", filteredExecutionRepository.count());
//        return stats;
//    }
//
//}
