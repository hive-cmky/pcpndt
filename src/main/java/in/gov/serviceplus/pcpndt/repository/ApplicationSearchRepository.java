package in.gov.serviceplus.pcpndt.repository;

import in.gov.serviceplus.pcpndt.entity.ApplicationMaster;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ApplicationSearchRepository {
    // Custom method to search across multiple fields
    List<ApplicationMaster> searchApplications(String searchTerm);

    // Get application with execution summary
    Map<String, Object> getApplicationSummary(String applRefNo);
}

