package com.edistrict.pcpndt.repository;

import com.edistrict.pcpndt.dto.FormHDataDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j  // ← ADD THIS ANNOTATION
@Repository
public class FormHRepository {

    @PersistenceContext
    private EntityManager entityManager;

//    public Optional<FormHDataDTO> findFormHDataByApplRefNo(String applRefNo) {
//        log.info("🔍 REPOSITORY: Searching for application: {}", applRefNo);
//
//        try {
//            // CORRECTED QUERY with proper column aliases
//            String simpleQuery = """
//            SELECT
//                ini.appl_id,
//                ini.appl_ref_no,
//                ini.applicant_name,
//                ini.name_of_centre as facility_name,
//                ini.mobile_no as phone_number,
//                ini.emails as email,
//                ini.application_type,
//                ini.submission_location,
//                ini.type_of_institution,
//                ini.type_of_ownership as ownership_type,
//                ini.ownership_name,
//                ini.ownership_address as applicant_address,
//                ini.routinglocationname as district
//            FROM pcpndt_initiated_data ini
//            WHERE ini.appl_ref_no = :applRefNo
//            """;
//
//            log.info("📝 Executing corrected query for: {}", applRefNo);
//
//            List<Object[]> results = entityManager.createNativeQuery(simpleQuery)
//                    .setParameter("applRefNo", applRefNo)
//                    .getResultList();
//
//            if (results.isEmpty()) {
//                log.warn("❌ REPOSITORY: No data found for: {}", applRefNo);
//                return Optional.empty();
//            }
//
//            log.info("✅ REPOSITORY: Found {} record(s) for: {}", results.size(), applRefNo);
//            Object[] row = results.get(0);
//            FormHDataDTO dto = mapSimpleRowToFormHDataDTO(row);
//
//            return Optional.of(dto);
//
//        } catch (Exception e) {
//            log.error("💥 REPOSITORY ERROR for {}: {}", applRefNo, e.getMessage());
//            e.printStackTrace();
//            return Optional.empty();
//        }
//    }
//
//    private FormHDataDTO mapSimpleRowToFormHDataDTO(Object[] row) {
//        FormHDataDTO dto = new FormHDataDTO();
//
//        dto.setApplId((String) row[0]);
//        dto.setApplRefNo((String) row[1]);
//        dto.setApplicantName((String) row[2]);
//        dto.setFacilityName((String) row[3]);
//        dto.setPhoneNumber((String) row[4]);
//        dto.setEmail((String) row[5]);
//        dto.setApplicationType((String) row[6]);
//        dto.setSubmissionLocation((String) row[7]);
//        dto.setTypeOfInstitution((String) row[8]);
//        dto.setOwnershipType((String) row[9]);
//        dto.setOwnershipName((String) row[10]);
//        dto.setApplicantAddress((String) row[11]);
//        dto.setDistrict((String) row[12]);
//
//        return dto;
//    }

    public List<String> findAllApplRefNos() {
        log.info("📋 REPOSITORY: Fetching all application references");
        String queryStr = "SELECT DISTINCT appl_ref_no FROM pcpndt_initiated_data ORDER BY appl_ref_no";
        List<String> result = entityManager.createNativeQuery(queryStr, String.class).getResultList();
        log.info("📊 REPOSITORY: Found {} application references", result.size());
        return result;
    }

    public Optional<FormHDataDTO> findFormHDataByApplRefNo(String applRefNo) {
        log.info("🔍 REPOSITORY: Searching for application: {}", applRefNo);

        try {
            // ENHANCED QUERY with joins to all three tables
            String comprehensiveQuery = """
            SELECT 
                ini.appl_id, 
                ini.appl_ref_no, 
                ini.applicant_name, 
                ini.name_of_centre as facility_name,
                ini.mobile_no as phone_number,
                ini.emails as email,
                ini.application_type,
                ini.submission_location,
                ini.type_of_institution,
                ini.type_of_ownership as ownership_type,
                ini.ownership_name,
                ini.ownership_address as applicant_address,
                ini.routinglocationname as district,
                ini.submission_date,
                ini.payment_reference_no,
                ini.payment_date,
                ini.payment_amount,
                -- Execution data
                exec.taskname,
                exec.status as execution_status,
                exec.receivedtime,
                exec.executiontime,
                exec.username as executed_by,
                -- Certificate data
                cert.officialform as certificate_data,
                cert.status as certificate_status
            FROM pcpndt_initiated_data ini
            LEFT JOIN pcpndt_execution_data exec ON ini.appl_id = exec.application_id
            LEFT JOIN pcpndt_filtered_execution cert ON ini.appl_id = cert.application_id
            WHERE ini.appl_ref_no = :applRefNo
            ORDER BY exec.receivedtime DESC
            LIMIT 1
            """;

            log.info("Executing comprehensive query for: {}", applRefNo);

            List<Object[]> results = entityManager.createNativeQuery(comprehensiveQuery)
                    .setParameter("applRefNo", applRefNo)
                    .getResultList();

            if (results.isEmpty()) {
                log.warn("REPOSITORY: No data found for: {}", applRefNo);
                return Optional.empty();
            }

            log.info("✅ REPOSITORY: Found comprehensive data for: {}", applRefNo);
            Object[] row = results.get(0);
            FormHDataDTO dto = mapComprehensiveRowToFormHDataDTO(row);

            return Optional.of(dto);

        } catch (Exception e) {
            log.error("💥 REPOSITORY ERROR for {}: {}", applRefNo, e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private FormHDataDTO mapComprehensiveRowToFormHDataDTO(Object[] row) {
        FormHDataDTO dto = new FormHDataDTO();

        // Basic application data
        dto.setApplId((String) row[0]);
        dto.setApplRefNo((String) row[1]);
        dto.setApplicantName((String) row[2]);
        dto.setFacilityName((String) row[3]);
        dto.setPhoneNumber((String) row[4]);
        dto.setEmail((String) row[5]);
        dto.setApplicationType((String) row[6]);
        dto.setSubmissionLocation((String) row[7]);
        dto.setTypeOfInstitution((String) row[8]);
        dto.setOwnershipType((String) row[9]);
        dto.setOwnershipName((String) row[10]);
        dto.setApplicantAddress((String) row[11]);
        dto.setDistrict((String) row[12]);

        // Payment info
        dto.setFileNumber((String) row[13]); // Using submission_date as file number
        dto.setRegistrationNumber((String) row[14]); // Using payment_reference_no as reg number

        // Set default values
        dto.setOutcome("GRANTED");
        dto.setFormType("Form B");
        dto.setRecommendationSummary("Recommended for approval");

        log.info("🗺️ REPOSITORY: Successfully mapped comprehensive DTO for: {}", dto.getApplicantName());
        return dto;
    }
}