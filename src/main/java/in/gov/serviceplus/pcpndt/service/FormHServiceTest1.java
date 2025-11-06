//package in.gov.serviceplus.pcpndt.service;
//
//import in.gov.serviceplus.pcpndt.dto.FormHRecordDTO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service  // ← THIS IS CRITICAL
//public class FormHService {
//
//    private static final Logger logger = LoggerFactory.getLogger(FormHService.class);
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public FormHRecordDTO findByReferenceNumber(String referenceNumber) {
//        String sql = """
//            SELECT
//                init.appl_id as applicationId,
//                init.submission_location as authorityName,
//                init.submission_date as registrationDate,
//                CONCAT(
//                    init.applicant_name,
//                    '\\nMobile: ', COALESCE(init.mobile_no, 'N/A'),
//                    '\\nEmail: ', COALESCE(init.emails, 'N/A')
//                ) as applicantDetails,
//                init.name_of_centre as centreName,
//                COALESCE(
//                    'Advisory Committee: ' || exec.taskname || ' on ' || exec.executiontime,
//                    'Pending Advisory Review'
//                ) as advisorySummary,
//                CASE
//                    WHEN filt.status = 'Approve' THEN 'GRANTED - Order issued on ' || COALESCE(filt.executiontime, '')
//                    WHEN filt.status = 'Reject' THEN 'REJECTED - Order issued on ' || COALESCE(filt.executiontime, '')
//                    ELSE 'UNDER PROCESS'
//                END as applicationOutcome,
//                COALESCE(
//                    filt.officialform ->> '143895',
//                    'Not yet assigned'
//                ) as registrationNumber,
//                CASE
//                    WHEN init.application_type LIKE '%Renewal%' THEN 'Renewal applied on: ' || init.submission_date
//                    ELSE 'Initial Registration'
//                END as renewalDetails,
//                init.appl_ref_no as fileNumber,
//                init.application_type as additionalInfo
//            FROM pcpndt_initiated_data init
//            LEFT JOIN (
//                SELECT DISTINCT ON (application_id) application_id, taskname, status, executiontime
//                FROM pcpndt_execution_data
//                WHERE taskname LIKE '%Advisory%' OR taskname LIKE '%Committee%' OR taskname LIKE '%DAA%'
//                ORDER BY application_id, executiontime DESC
//            ) exec ON init.appl_id = exec.application_id
//            LEFT JOIN (
//                SELECT DISTINCT ON (application_id) application_id, status, executiontime, officialform
//                FROM pcpndt_filtered_execution
//                ORDER BY application_id, executiontime DESC
//            ) filt ON init.appl_id = filt.application_id
//            WHERE init.appl_ref_no = ?
//            """;
//
//        try {
//            List<FormHRecordDTO> results = jdbcTemplate.query(sql,
//                    new BeanPropertyRowMapper<>(FormHRecordDTO.class),
//                    referenceNumber
//            );
//
//            return results.isEmpty() ? null : results.get(0);
//
//        } catch (Exception e) {
//            logger.error("Error finding Form-H record by reference number {}: {}", referenceNumber, e.getMessage());
//            return null;
//        }
//    }
//}