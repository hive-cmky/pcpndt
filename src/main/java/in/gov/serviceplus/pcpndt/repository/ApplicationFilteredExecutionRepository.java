package in.gov.serviceplus.pcpndt.repository;

import in.gov.serviceplus.pcpndt.entity.ApplicationFilteredExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationFilteredExecutionRepository extends JpaRepository<ApplicationFilteredExecution, Long> {
    // Find all filtered execution records by application_id
    List<ApplicationFilteredExecution> findByApplicationId(String applicationId);

    // Find by application_id and status
    List<ApplicationFilteredExecution> findByApplicationIdAndStatus(String applicationId, String status);

    // Find by task name
    List<ApplicationFilteredExecution> findByTaskName(String taskName);

    // Find recent executions
    List<ApplicationFilteredExecution> findTop10ByOrderByIdDesc();
}
