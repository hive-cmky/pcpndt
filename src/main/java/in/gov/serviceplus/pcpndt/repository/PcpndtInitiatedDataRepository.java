package in.gov.serviceplus.pcpndt.repository;
//
//import in.gov.serviceplus.pcpndt.entity.PcpndtInitiatedData;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//
//@Repository
//public interface PcpndtInitiatedDataRepository extends JpaRepository<PcpndtInitiatedData, Long> {
//    List<PcpndtInitiatedData> findAll();
//
//    @Query("SELECT p FROM PcpndtInitiatedData p WHERE p.application_type LIKE %:type%")
//    List<PcpndtInitiatedData> findByApplicationType(@Param("type") String type);
//
//    @Query(value = "SELECT * FROM pcpndt_initiated_data", nativeQuery = true)
//    List<PcpndtInitiatedData> findAllNative();
//}
//
//package in.gov.serviceplus.pcpndt.repository;

import in.gov.serviceplus.pcpndt.entity.PcpndtInitiatedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PcpndtInitiatedDataRepository extends JpaRepository<PcpndtInitiatedData, Long> {

    List<PcpndtInitiatedData> findAll();

    @Query(value = "SELECT * FROM pcpndt_initiated_data", nativeQuery = true)
    List<PcpndtInitiatedData> findAllNative();

    @Query("SELECT p FROM PcpndtInitiatedData p WHERE p.application_type LIKE %:type%")
    List<PcpndtInitiatedData> findByApplicationType(@Param("type") String type);
}
