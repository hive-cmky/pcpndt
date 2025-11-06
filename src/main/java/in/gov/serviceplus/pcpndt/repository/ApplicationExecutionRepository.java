package in.gov.serviceplus.pcpndt.repository;

import in.gov.serviceplus.pcpndt.entity.ApplicationExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationExecutionRepository extends JpaRepository<ApplicationExecution, Long> {
    // Find all execution records by application_id
    List<ApplicationExecution> findByApplicationId(String applicationId);

    // Find by application_id and status
    List<ApplicationExecution> findByApplicationIdAndStatus(String applicationId, String status);

    // Find by task name
    List<ApplicationExecution> findByTaskName(String taskName);

    // Find by status
    List<ApplicationExecution> findByStatus(String status);
}
