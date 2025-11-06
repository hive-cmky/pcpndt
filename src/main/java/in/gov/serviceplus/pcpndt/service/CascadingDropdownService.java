package in.gov.serviceplus.pcpndt.service;

import in.gov.serviceplus.pcpndt.entity.ApplicationMaster;
import in.gov.serviceplus.pcpndt.repository.ApplicationMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CascadingDropdownService {

    @Autowired
    private ApplicationMasterRepository applicationMasterRepository;

    /**
     * Get all available services for dropdown
     */
    public List<String> getAllServices() {
        List<String> services = applicationMasterRepository.findAllDistinctServices();
        return services.isEmpty() ? List.of("PC & PNDT") : services;
    }

    /**
     * Get all districts (extracted from submission locations) - NO LIMITS
     */
    public List<String> getAllDistricts() {
        return applicationMasterRepository.findAllDistinctDistricts();
    }

    /**
     * Get centres based on selected district - NO LIMITS
     */
    public List<String> getCentresByDistrict(String district) {
        if (district == null || district.trim().isEmpty()) {
            return List.of();
        }
        return applicationMasterRepository.findCentresByDistrict(district);
    }

    /**
     * Get application reference numbers based on selected centre - NO LIMITS
     */
    public List<String> getApplicationRefNosByCentre(String centreName) {
        if (centreName == null || centreName.trim().isEmpty()) {
            return List.of();
        }
        return applicationMasterRepository.findApplicationRefNosByCentre(centreName);
    }

    /**
     * Get full application details for Form H generation
     */
    public ApplicationMaster getApplicationDetails(String applRefNo) {
        if (applRefNo == null || applRefNo.trim().isEmpty()) {
            return null;
        }
        return applicationMasterRepository.findByApplRefNo(applRefNo).orElse(null);
    }

    /**
     * Get all dropdown data at once (for page load)
     */
    public Map<String, Object> getInitialDropdownData() {
        Map<String, Object> data = new HashMap<>();
        data.put("services", getAllServices());
        data.put("districts", getAllDistricts());
        data.put("centres", List.of()); // Empty initially
        data.put("applicationRefNos", List.of()); // Empty initially
        return data;
    }
}