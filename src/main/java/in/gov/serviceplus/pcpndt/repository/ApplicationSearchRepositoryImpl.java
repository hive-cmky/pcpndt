package in.gov.serviceplus.pcpndt.repository;

import in.gov.serviceplus.pcpndt.entity.ApplicationMaster;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ApplicationSearchRepositoryImpl implements ApplicationSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ApplicationMaster> searchApplications(String searchTerm) {
        String query = "SELECT a FROM ApplicationMaster a WHERE " +
                "a.applRefNo LIKE :searchTerm OR " +
                "a.applicantName LIKE :searchTerm OR " +
                "a.mobileNo LIKE :searchTerm OR " +
                "a.applId LIKE :searchTerm";

        return entityManager.createQuery(query, ApplicationMaster.class)
                .setParameter("searchTerm", "%" + searchTerm + "%")
                .getResultList();
    }

    @Override
    public Map<String, Object> getApplicationSummary(String applRefNo) {
        // Implementation for complex joins
        return new HashMap<>();
    }
}
