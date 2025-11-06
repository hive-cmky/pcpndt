package in.gov.serviceplus.pcpndt.repository;

import in.gov.serviceplus.pcpndt.entity.PcpndtExecutionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;




//@Repository
//public interface PcpndtExecutionDataRepository extends JpaRepository<PcpndtExecutionData, Long> {
//    List<PcpndtExecutionData> findByApplication_id(Long applicationId);
//
//    // Or use @Query for clarity
//    @Query("SELECT e FROM PcpndtExecutionData e WHERE e.application_id = :applicationId")
//    List<PcpndtExecutionData> findByApplicationId(@Param("applicationId") Long applicationId);
//
//    @Query("SELECT e FROM PcpndtExecutionData e WHERE e.application_id IN :applicationIds")
//    List<PcpndtExecutionData> findByApplicationIds(@Param("applicationIds") List<Long> applicationIds);
//
////    @Query("SELECT e FROM PcpndtExecutionData e WHERE e.application_id IN :applicationIds")
////    List<PcpndtExecutionData> findByApplicationIds(@Param("applicationIds") List<Long> applicationIds);
//}

@Repository
public interface PcpndtExecutionDataRepository extends JpaRepository<PcpndtExecutionData, Long> {

    @Query(value = "SELECT * FROM pcpndt_execution_data WHERE application_id = :applicationId", nativeQuery = true)
    List<PcpndtExecutionData> findByApplicationId(@Param("applicationId") Long applicationId);

    @Query(value = "SELECT * FROM pcpndt_execution_data WHERE application_id IN :applicationIds", nativeQuery = true)
    List<PcpndtExecutionData> findByApplicationIds(@Param("applicationIds") List<Long> applicationIds);
}