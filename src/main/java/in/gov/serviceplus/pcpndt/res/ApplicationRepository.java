//package in.gov.serviceplus.pcpndt.repository;
//
//import in.gov.serviceplus.pcpndt.entity.Application;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
////@Repository
////public interface ApplicationRepository extends JpaRepository<Application, String> {
////    List<Application> findByDistrictContainingIgnoreCase(String district);
////    List<Application> findByApplicationTypeContainingIgnoreCase(String applicationType);
////    List<Application> findByApplicantNameContainingIgnoreCase(String applicantName);
////}
//
//@Repository
//public interface ApplicationRepository extends JpaRepository<Application, Long> {
//    List<Application> findAll();
//
//    @Query("SELECT a FROM Application a WHERE a.applicationType LIKE %:type%")
//    List<Application> findByApplicationType(@Param("type") String type);
//
//    @Query("SELECT a FROM Application a WHERE a.nameOfCentre LIKE %:name%")
//    List<Application> findByCentreName(@Param("name") String name);
//}
