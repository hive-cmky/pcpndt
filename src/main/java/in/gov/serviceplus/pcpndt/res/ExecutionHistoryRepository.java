//package in.gov.serviceplus.pcpndt.repository;
//
//import in.gov.serviceplus.pcpndt.entity.ExecutionHistory;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
////public interface ExecutionHistoryRepository extends JpaRepository<ExecutionHistory, Long> {
////    List<ExecutionHistory> findByApplicationId(String applicationId);
////    List<ExecutionHistory> findByStatus(String status);
////}
//
//@Repository
//public interface ExecutionHistoryRepository extends JpaRepository<ExecutionHistory, Long> {
//    List<ExecutionHistory> findByApplicationId(Long applicationId);
//
//    @Query("SELECT e FROM ExecutionHistory e WHERE e.applicationId IN :applicationIds")
//    List<ExecutionHistory> findByApplicationIds(@Param("applicationIds") List<Long> applicationIds);
//}
//
