package in.gov.serviceplus.pcpndt.repository;


import in.gov.serviceplus.pcpndt.entity.PcpndtFilteredExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Repository
//public interface PcpndtFilteredExecutionRepository extends JpaRepository<PcpndtFilteredExecution, Long> {
////    List<PcpndtFilteredExecution> findByApplication_id(Long applicationId);
////    List<PcpndtFilteredExecution> findByStatus(String status);
//
//    // Use exact field name with underscore
//    List<PcpndtFilteredExecution> findByApplication_id(Long application_id);
//
//    // Or use @Query for clarity
//    @Query("SELECT f FROM PcpndtFilteredExecution f WHERE f.application_id = :applicationId")
//    List<PcpndtFilteredExecution> findByApplicationId(@Param("applicationId") Long applicationId);
//
//    List<PcpndtFilteredExecution> findByStatus(String status);
//}

@Repository
public interface PcpndtFilteredExecutionRepository extends JpaRepository<PcpndtFilteredExecution, Long> {

    @Query(value = "SELECT * FROM pcpndt_filtered_execution WHERE application_id = :applicationId", nativeQuery = true)
    List<PcpndtFilteredExecution> findByApplicationId(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT * FROM pcpndt_filtered_execution WHERE status = :status", nativeQuery = true)
    List<PcpndtFilteredExecution> findByStatus(@Param("status") String status);
}