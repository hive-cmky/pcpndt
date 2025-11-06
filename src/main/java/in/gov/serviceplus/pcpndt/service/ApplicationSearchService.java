package in.gov.serviceplus.pcpndt.service;


import in.gov.serviceplus.pcpndt.dto.response.ApplicationSearchResult;
import in.gov.serviceplus.pcpndt.entity.ApplicationExecution;
import in.gov.serviceplus.pcpndt.entity.ApplicationFilteredExecution;
import in.gov.serviceplus.pcpndt.entity.ApplicationMaster;
import in.gov.serviceplus.pcpndt.repository.ApplicationExecutionRepository;
import in.gov.serviceplus.pcpndt.repository.ApplicationFilteredExecutionRepository;
import in.gov.serviceplus.pcpndt.repository.ApplicationMasterRepository;
import in.gov.serviceplus.pcpndt.service.exception.ApplicationNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ApplicationSearchService {
    private final ApplicationMasterRepository applicationMasterRepository;
    private final ApplicationExecutionRepository applicationExecutionRepository;
    private final ApplicationFilteredExecutionRepository applicationFilteredExecutionRepository;

    // Constructor injection (recommended)
    public ApplicationSearchService(
            ApplicationMasterRepository applicationMasterRepository,
            ApplicationExecutionRepository applicationExecutionRepository,
            ApplicationFilteredExecutionRepository applicationFilteredExecutionRepository) {
        this.applicationMasterRepository = applicationMasterRepository;
        this.applicationExecutionRepository = applicationExecutionRepository;
        this.applicationFilteredExecutionRepository = applicationFilteredExecutionRepository;
    }

    /**
     * Search application by appl_ref_no and get all related data
     */
    public ApplicationSearchResult searchByApplRefNo(String applRefNo) {
        // Step 1: Find application master data by appl_ref_no
        ApplicationMaster applicationMaster = applicationMasterRepository.findByApplRefNo(applRefNo)
                .orElseThrow(() -> new ApplicationNotFoundException(
                        "Application not found with reference number: " + applRefNo));

        // Step 2: Get appl_id (which is same as application_id in other tables)
        String applicationId = applicationMaster.getApplId();

        // Step 3: Fetch execution data from both execution tables
        List<ApplicationExecution> executionHistory = applicationExecutionRepository.findByApplicationId(applicationId);
        List<ApplicationFilteredExecution> filteredExecutions = applicationFilteredExecutionRepository.findByApplicationId(applicationId);

        // Step 4: Create and return combined result
        return new ApplicationSearchResult(applicationMaster, executionHistory, filteredExecutions);
    }

    /**
     * Search application by appl_id
     */
    public ApplicationSearchResult searchByApplId(String applId) {
        ApplicationMaster applicationMaster = applicationMasterRepository.findByApplId(applId)
                .orElseThrow(() -> new ApplicationNotFoundException(
                        "Application not found with ID: " + applId));

        List<ApplicationExecution> executionHistory = applicationExecutionRepository.findByApplicationId(applId);
        List<ApplicationFilteredExecution> filteredExecutions = applicationFilteredExecutionRepository.findByApplicationId(applId);

        return new ApplicationSearchResult(applicationMaster, executionHistory, filteredExecutions);
    }

    /**
     * Get only basic application info (without execution data)
     */
    public ApplicationMaster getApplicationBasicInfo(String applRefNo) {
        return applicationMasterRepository.findByApplRefNo(applRefNo)
                .orElseThrow(() -> new ApplicationNotFoundException(
                        "Application not found with reference number: " + applRefNo));
    }
}
