//// package in.gov.serviceplus.pcpndt.service;
//////
//////import in.gov.serviceplus.pcpndt.entity.PcpndtExecutionData;
//////import in.gov.serviceplus.pcpndt.entity.PcpndtFilteredExecution;
//////import in.gov.serviceplus.pcpndt.entity.PcpndtInitiatedData;
//////import in.gov.serviceplus.pcpndt.repository.PcpndtExecutionDataRepository;
//////import in.gov.serviceplus.pcpndt.repository.PcpndtFilteredExecutionRepository;
//////import in.gov.serviceplus.pcpndt.repository.PcpndtInitiatedDataRepository;
//////import jakarta.persistence.EntityManager;
//////import jakarta.persistence.PersistenceContext;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.stereotype.Service;
//////
//////import java.util.HashMap;
//////import java.util.List;
//////import java.util.Map;
//////
//////////package in.gov.serviceplus.pcpndt.service;
//////////
//////////import in.gov.serviceplus.pcpndt.entity.Application;
//////////import in.gov.serviceplus.pcpndt.entity.ExecutionHistory;
//////////import in.gov.serviceplus.pcpndt.entity.FilteredExecution;
//////////import in.gov.serviceplus.pcpndt.repository.ApplicationRepository;
//////////import in.gov.serviceplus.pcpndt.repository.ExecutionHistoryRepository;
//////////import in.gov.serviceplus.pcpndt.repository.FilteredExecutionRepository;
//////////import org.springframework.beans.factory.annotation.Autowired;
//////////import org.springframework.stereotype.Service;
//////////
//////////import java.util.HashMap;
//////////import java.util.List;
//////////import java.util.Map;
//////////
//////////@Service
//////////public class ApplicationService {
//////////
//////////    @Autowired
//////////    private ApplicationRepository applicationRepository;
//////////
//////////    @Autowired
//////////    private ExecutionHistoryRepository executionHistoryRepository;
//////////
//////////    @Autowired
//////////    private FilteredExecutionRepository filteredExecutionRepository;
//////////
//////////    public List<Application> getAllApplications() {
//////////        return applicationRepository.findAll();
//////////    }
//////////
//////////    public Application getApplicationById(Long id) {
//////////        return applicationRepository.findById(id).orElse(null);
//////////    }
//////////
//////////    public List<ExecutionHistory> getExecutionHistory(Long applicationId) {
//////////        return executionHistoryRepository.findByApplicationId(applicationId);
//////////    }
//////////
//////////    public List<FilteredExecution> getFilteredExecutions(Long applicationId) {
//////////        return filteredExecutionRepository.findByApplicationId(applicationId);
//////////    }
//////////
//////////    public Map<String, Object> getApplicationDetails(Long applicationId) {
//////////        Map<String, Object> details = new HashMap<>();
//////////
//////////        Application application = getApplicationById(applicationId);
//////////        details.put("application", application);
//////////
//////////        if (application != null) {
//////////            details.put("executionHistory", getExecutionHistory(applicationId));
//////////            details.put("filteredExecutions", getFilteredExecutions(applicationId));
//////////        }
//////////
//////////        return details;
//////////    }
//////////}
////////
////////import in.gov.serviceplus.pcpndt.entity.PcpndtExecutionData;
////////import in.gov.serviceplus.pcpndt.entity.PcpndtFilteredExecution;
////////import in.gov.serviceplus.pcpndt.entity.PcpndtInitiatedData;
////////import in.gov.serviceplus.pcpndt.repository.PcpndtExecutionDataRepository;
////////import in.gov.serviceplus.pcpndt.repository.PcpndtFilteredExecutionRepository;
////////import in.gov.serviceplus.pcpndt.repository.PcpndtInitiatedDataRepository;
////////import org.springframework.beans.factory.annotation.Autowired;
////////import org.springframework.stereotype.Service;
////////
////////import java.util.HashMap;
////////import java.util.List;
////////import java.util.Map;
////////
////////@Service
////////public class ApplicationService {
////////
////////    @Autowired
////////    private PcpndtInitiatedDataRepository initiatedDataRepository;
////////
////////    @Autowired
////////    private PcpndtExecutionDataRepository executionDataRepository;
////////
////////    @Autowired
////////    private PcpndtFilteredExecutionRepository filteredExecutionRepository;
////////
////////    public List<PcpndtInitiatedData> getAllApplications() {
////////        // Try native query first to ensure we get data
////////        List<PcpndtInitiatedData> apps = initiatedDataRepository.findAllNative();
////////        System.out.println("Native query found: " + apps.size() + " applications");
////////
////////        if (apps.isEmpty()) {
////////            // Fallback to JPA method
////////            apps = initiatedDataRepository.findAll();
////////            System.out.println("JPA method found: " + apps.size() + " applications");
////////        }
////////
////////        return apps;
////////    }
////////
////////    public PcpndtInitiatedData getApplicationById(Long id) {
////////        return initiatedDataRepository.findById(id).orElse(null);
////////    }
////////
////////    public List<PcpndtExecutionData> getExecutionHistory(Long applicationId) {
////////        return executionDataRepository.findByApplication_id(applicationId);
////////    }
////////
////////    public List<PcpndtFilteredExecution> getFilteredExecutions(Long applicationId) {
////////        return filteredExecutionRepository.findByApplication_id(applicationId);
////////    }
////////
////////    public Map<String, Object> getApplicationDetails(Long applicationId) {
////////        Map<String, Object> details = new HashMap<>();
////////
////////        PcpndtInitiatedData application = getApplicationById(applicationId);
////////        details.put("application", application);
////////
////////        if (application != null) {
////////            details.put("executionHistory", getExecutionHistory(applicationId));
////////            details.put("filteredExecutions", getFilteredExecutions(applicationId));
////////        }
////////
////////        return details;
////////    }
////////}
//////
//////@Service
//////public class ApplicationService {
//////
//////    @Autowired
//////    private PcpndtInitiatedDataRepository initiatedDataRepository;
//////
//////    @Autowired
//////    private PcpndtExecutionDataRepository executionDataRepository;
//////
//////    @Autowired
//////    private PcpndtFilteredExecutionRepository filteredExecutionRepository;
//////
//////    public List<PcpndtInitiatedData> getAllApplications() {
//////        // Try native query first to ensure we get data
//////        List<PcpndtInitiatedData> apps = initiatedDataRepository.findAllNative();
//////        System.out.println("Native query found: " + apps.size() + " applications");
//////
//////        if (apps.isEmpty()) {
//////            // Fallback to JPA method
//////            apps = initiatedDataRepository.findAll();
//////            System.out.println("JPA method found: " + apps.size() + " applications");
//////        }
//////
//////        return apps;
//////    }
//////
//////    public PcpndtInitiatedData getApplicationById(Long id) {
//////        return initiatedDataRepository.findById(id).orElse(null);
//////    }
//////
//////    public List<PcpndtExecutionData> getExecutionHistory(Long applicationId) {
//////        return executionDataRepository.findByApplicationId(applicationId);
//////    }
//////
//////    public List<PcpndtFilteredExecution> getFilteredExecutions(Long applicationId) {
//////        return filteredExecutionRepository.findByApplicationId(applicationId);
//////    }
//////
////
////import in.gov.serviceplus.pcpndt.entity.PcpndtExecutionData;
////import in.gov.serviceplus.pcpndt.entity.PcpndtFilteredExecution;
////import in.gov.serviceplus.pcpndt.repository.PcpndtInitiatedDataRepository;
////import jakarta.persistence.EntityManager;
////import jakarta.persistence.PersistenceContext;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////
////////    public List<PcpndtExecutionData> getExecutionHistory(Long applicationId) {
////////        // Use the method with exact field name
////////        return executionDataRepository.findByApplication_id(applicationId);
////////    }
////////
////////    public List<PcpndtFilteredExecution> getFilteredExecutions(Long applicationId) {
////////        // Use the method with exact field name
////////        return filteredExecutionRepository.findByApplication_id(applicationId);
////////    }
//////
//////    @PersistenceContext
//////    private EntityManager entityManager;
//////
//////    public List<PcpndtExecutionData> getExecutionHistory(Long applicationId) {
//////        // Use EntityManager for manual query
//////        String jpql = "SELECT e FROM PcpndtExecutionData e WHERE e.application_id = :appId";
//////        return entityManager.createQuery(jpql, PcpndtExecutionData.class)
//////                .setParameter("appId", applicationId)
//////                .getResultList();
//////    }
//////
//////    public Map<String, Object> getApplicationDetails(Long applicationId) {
//////        Map<String, Object> details = new HashMap<>();
//////
//////        PcpndtInitiatedData application = getApplicationById(applicationId);
//////        details.put("application", application);
//////
//////        if (application != null) {
//////            details.put("executionHistory", getExecutionHistory(applicationId));
//////            details.put("filteredExecutions", getFilteredExecutions(applicationId));
//////        }
//////
//////        return details;
//////    }
//////
//////}
////
////@Service
////public class ApplicationService {
////
////    @Autowired
////    private PcpndtInitiatedDataRepository initiatedDataRepository;
////
////    @PersistenceContext
////    private EntityManager entityManager;
////
////    // REMOVE this - we don't need the repository if using EntityManager
////    // @Autowired
////    // private PcpndtExecutionDataRepository executionDataRepository;
////
////    // KEEP ONLY THIS VERSION
////    public List<PcpndtExecutionData> getExecutionHistory(Long applicationId) {
////        String jpql = "SELECT e FROM PcpndtExecutionData e WHERE e.application_id = :appId";
////        return entityManager.createQuery(jpql, PcpndtExecutionData.class)
////                .setParameter("appId", applicationId)
////                .getResultList();
////    }
////
////    public List<PcpndtFilteredExecution> getFilteredExecutions(Long applicationId) {
////        String jpql = "SELECT f FROM PcpndtFilteredExecution f WHERE f.application_id = :appId";
////        return entityManager.createQuery(jpql, PcpndtFilteredExecution.class)
////                .setParameter("appId", applicationId)
////                .getResultList();
////    }
////
////    // ... rest of your methods
////}
////
////
//
//package in.gov.serviceplus.pcpndt.service;
//
//import in.gov.serviceplus.pcpndt.entity.ApplicationMaster;
//import in.gov.serviceplus.pcpndt.entity.PcpndtInitiatedData;
//import in.gov.serviceplus.pcpndt.entity.PcpndtExecutionData;
//import in.gov.serviceplus.pcpndt.entity.PcpndtFilteredExecution;
//import in.gov.serviceplus.pcpndt.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class ApplicationService {
//
//    @Autowired
//    private ApplicationMasterRepository applicationMasterRepository;
//
//    @Autowired
//    private ApplicationExecutionRepository applicationExecutionRepository;
//
//    @Autowired
//    private ApplicationFilteredExecutionRepository applicationFilteredExecutionRepository;
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    // Add this missing method
//    public List<ApplicationMaster> getAllApplications() {
//        // Try native query first to ensure we get data
//        List<ApplicationMaster> apps = applicationMasterRepository.findAllNative();
//        System.out.println("Native query found: " + apps.size() + " applications");
//
//        if (apps.isEmpty()) {
//            // Fallback to JPA method
//            apps = applicationMasterRepository.findAll();
//            System.out.println("JPA method found: " + apps.size() + " applications");
//        }
//
//        return apps;
//    }
//
////    public ApplicationMasterRepository getApplicationById(Long id) {
////        return applicationMasterRepository.findById(id).orElse(null);
////    }
//
//    public List<PcpndtExecutionData> getExecutionHistory(Long applicationId) {
//        // Use EntityManager for manual query to avoid method naming issues
//        String jpql = "SELECT e FROM PcpndtExecutionData e WHERE e.application_id = :appId";
//        return entityManager.createQuery(jpql, PcpndtExecutionData.class)
//                .setParameter("appId", applicationId)
//                .getResultList();
//    }
//
//    public List<PcpndtFilteredExecution> getFilteredExecutions(Long applicationId) {
//        // Use EntityManager for manual query to avoid method naming issues
//        String jpql = "SELECT f FROM PcpndtFilteredExecution f WHERE f.application_id = :appId";
//        return entityManager.createQuery(jpql, PcpndtFilteredExecution.class)
//                .setParameter("appId", applicationId)
//                .getResultList();
//    }
//
////    public Map<String, Object> getApplicationDetails(Long applicationId) {
////        Map<String, Object> details = new HashMap<>();
////
////        ApplicationMasterRepository application = getApplicationById(applicationId);
////        details.put("application", application);
////
////        if (application != null) {
////            details.put("executionHistory", getExecutionHistory(applicationId));
////            details.put("filteredExecutions", getFilteredExecutions(applicationId));
////        }
////
////        return details;
////    }
//}
