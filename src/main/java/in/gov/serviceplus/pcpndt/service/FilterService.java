package in.gov.serviceplus.pcpndt.service;

import in.gov.serviceplus.pcpndt.entity.ApplicationMaster;
import in.gov.serviceplus.pcpndt.repository.ApplicationMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterService {
    @Autowired
    private ApplicationMasterRepository applicationMasterRepository;

    public List<String> getAllServices() {
        return List.of("PC PNDT");
    }

    public List<String> getAllDistricts() {
        List<String> allSubmissionLocations = applicationMasterRepository.findAllDistinctSubmissionLocations();

        return allSubmissionLocations.stream()
                .map(this::extractDistrictFromSubmissionLocation)
                .filter(district -> district != null && !district.trim().isEmpty())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public String extractDistrictFromSubmissionLocation(String submissionLocation) {
        if (submissionLocation == null || submissionLocation.trim().isEmpty()) {
            return null;
        }

        try {
            if (submissionLocation.contains("DISTRICT -")) {
                String[] parts = submissionLocation.split("DISTRICT -");
                if (parts.length > 1) {
                    String districtPart = parts[1].trim();
                    if (districtPart.contains(")")) {
                        return districtPart.split("\\)")[0].trim();
                    }
                    return districtPart;
                }
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error parsing district from: " + submissionLocation);
            return null;
        }
    }

    // FIXED: This method now uses the correct repository method
    public List<ApplicationMaster> getApplicationsByDistrict(String district) {
        List<String> allLocations = applicationMasterRepository.findAllDistinctSubmissionLocations();

        List<String> matchingLocations = allLocations.stream()
                .filter(location -> {
                    String extractedDistrict = extractDistrictFromSubmissionLocation(location);
                    return extractedDistrict != null && extractedDistrict.equalsIgnoreCase(district);
                })
                .collect(Collectors.toList());

        // FIXED: Use the correct method that accepts List<String>
        return applicationMasterRepository.findBySubmissionLocations(matchingLocations);
    }

    public List<ApplicationMaster> getApplicationsByServiceAndDistrict(String service, String district) {
        return getApplicationsByDistrict(district);
    }

    // NEW: Get all services from database (dynamic)
    public List<String> getAllServicesFromDB() {
        return applicationMasterRepository.findAllDistinctServices();
    }

    // NEW: Get centres by district for cascading dropdown
    public List<String> getCentresByDistrict(String district) {
        return applicationMasterRepository.findCentresByDistrict(district);
    }

    // NEW: Get application reference numbers by centre for cascading dropdown
    public List<String> getApplicationRefNosByCentre(String centreName) {
        return applicationMasterRepository.findApplicationRefNosByCentre(centreName);
    }

    // NEW: Get full application by reference number for Form H generation
    public ApplicationMaster getApplicationByRefNo(String applRefNo) {
        return applicationMasterRepository.findByApplRefNo(applRefNo)
                .orElse(null);
    }

    public ApplicationMasterRepository getApplicationMasterRepository() {
        return applicationMasterRepository;
    }
}