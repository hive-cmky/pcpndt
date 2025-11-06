package in.gov.serviceplus.pcpndt.repository;

import in.gov.serviceplus.pcpndt.entity.ApplicationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationMasterRepository extends JpaRepository<ApplicationMaster, String> {

    // ========== EXISTING METHODS (KEEP THESE) ==========

    // Find by appl_ref_no
    Optional<ApplicationMaster> findByApplRefNo(String applRefNo);

    // Find by appl_id
    Optional<ApplicationMaster> findByApplId(String applId);

    // Find by applicant name (for future use)
    List<ApplicationMaster> findByApplicantNameContainingIgnoreCase(String applicantName);

    // Find by mobile number
    Optional<ApplicationMaster> findByMobileNo(String mobileNo);

    // Get distinct submission locations (full format)
    @Query("SELECT DISTINCT a.submissionLocation FROM ApplicationMaster a WHERE a.submissionLocation IS NOT NULL")
    List<String> findAllDistinctSubmissionLocations();

    // Find applications by submission locations
    List<ApplicationMaster> findBySubmissionLocationIn(List<String> submissionLocations);

    // Get only district names from submission locations
    @Query(value = """
        SELECT DISTINCT 
            CASE 
                WHEN submission_location LIKE '%DISTRICT - %' THEN
                    TRIM(SPLIT_PART(SPLIT_PART(submission_location, 'DISTRICT - ', 2), ')', 1))
                ELSE submission_location
            END as district
        FROM pcpndt_initiated_data 
        WHERE submission_location IS NOT NULL 
        ORDER BY district
        """, nativeQuery = true)
    List<String> findAllDistinctDistricts();

    // FIXED: Find applications by single district name
    @Query(value = """
        SELECT * FROM pcpndt_initiated_data 
        WHERE submission_location LIKE '%' || :district || '%'
        """, nativeQuery = true)
    List<ApplicationMaster> findByDistrict(@Param("district") String district);

    // FIXED: Find applications by multiple locations (use this for your FilterService)
    @Query(value = """
        SELECT * FROM pcpndt_initiated_data 
        WHERE submission_location IN :locations
        """, nativeQuery = true)
    List<ApplicationMaster> findBySubmissionLocations(@Param("locations") List<String> locations);

    // ========== NEW METHODS FOR CASCADING DROPDOWNS ==========

    // Get all distinct services
    @Query("SELECT DISTINCT a.serviceName FROM ApplicationMaster a WHERE a.serviceName IS NOT NULL ORDER BY a.serviceName")
    List<String> findAllDistinctServices();

    // Get centres by district (extracted from submission location)
    @Query(value = """
        SELECT DISTINCT name_of_centre 
        FROM pcpndt_initiated_data 
        WHERE submission_location LIKE '%' || :district || '%' 
        AND name_of_centre IS NOT NULL 
        AND name_of_centre != ''
        ORDER BY name_of_centre
        """, nativeQuery = true)
    List<String> findCentresByDistrict(@Param("district") String district);

    // Get application reference numbers by centre name
    @Query("SELECT DISTINCT a.applRefNo FROM ApplicationMaster a WHERE a.nameOfCentre = :centreName AND a.applRefNo IS NOT NULL ORDER BY a.applRefNo")
    List<String> findApplicationRefNosByCentre(@Param("centreName") String centreName);

    // Get full application details by reference number (for Form H generation)
    //Optional<ApplicationMaster> findByApplRefNo(String applRefNo); // This already exists above

    // Get centres by service and district (more specific filtering)
    @Query(value = """
        SELECT DISTINCT name_of_centre 
        FROM pcpndt_initiated_data 
        WHERE service_name = :serviceName 
        AND submission_location LIKE '%' || :district || '%' 
        AND name_of_centre IS NOT NULL 
        ORDER BY name_of_centre
        """, nativeQuery = true)
    List<String> findCentresByServiceAndDistrict(@Param("serviceName") String serviceName,
                                                 @Param("district") String district);

    // ========== UTILITY METHODS ==========

    // Check if application exists by reference number
    boolean existsByApplRefNo(String applRefNo);

    // Count applications by centre
    @Query("SELECT COUNT(a) FROM ApplicationMaster a WHERE a.nameOfCentre = :centreName")
    Long countByCentreName(@Param("centreName") String centreName);
}