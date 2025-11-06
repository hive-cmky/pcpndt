package com.edistrict.pcpndt.repository;

import com.edistrict.pcpndt.entity.PcpndtInitiatedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PcpndtInitiatedDataRepository extends JpaRepository<PcpndtInitiatedData, Long> {

    // Basic search methods
    Optional<PcpndtInitiatedData> findByApplRefNo(String applRefNo);

    List<PcpndtInitiatedData> findByApplRefNoContainingIgnoreCase(String applRefNo);

    List<PcpndtInitiatedData> findByApplicantNameContainingIgnoreCase(String applicantName);

    List<PcpndtInitiatedData> findByNameOfCentreContainingIgnoreCase(String nameOfCentre);

    List<PcpndtInitiatedData> findBySubmissionLocationContainingIgnoreCase(String submissionLocation);

    List<PcpndtInitiatedData> findByApplicationTypeContainingIgnoreCase(String applicationType);

    // Custom query methods
    @Query("SELECT p FROM PcpndtInitiatedData p WHERE LOWER(p.applRefNo) LIKE LOWER(CONCAT('%', :applRefNo, '%'))")
    List<PcpndtInitiatedData> searchByApplRefNo(@Param("applRefNo") String applRefNo);

    @Query("SELECT p FROM PcpndtInitiatedData p ORDER BY p.submissionDate DESC")
    List<PcpndtInitiatedData> findAllOrderBySubmissionDateDesc();

    @Query("SELECT p FROM PcpndtInitiatedData p WHERE LOWER(p.applicantName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.nameOfCentre) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<PcpndtInitiatedData> searchByApplicantNameOrCentreName(@Param("searchTerm") String searchTerm);

    // Advanced search combining multiple fields
    @Query("SELECT p FROM PcpndtInitiatedData p WHERE " +
            "LOWER(p.applRefNo) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.applicantName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.nameOfCentre) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.submissionLocation) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<PcpndtInitiatedData> comprehensiveSearch(@Param("searchTerm") String searchTerm);

    // Filter by application type
    List<PcpndtInitiatedData> findByApplicationType(String applicationType);
}
