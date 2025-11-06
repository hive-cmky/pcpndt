package in.gov.serviceplus.pcpndt.service;

import in.gov.serviceplus.pcpndt.dto.FormHRecordDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FormHService {

    private static final Logger logger = LoggerFactory.getLogger(FormHService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public FormHRecordDTO findByReferenceNumber(String referenceNumber) {
        // First, let's debug what's actually in the tables
        debugTableData(referenceNumber);

        String sql = """
            SELECT 
                init.appl_id as applicationId,
                init.submission_location as authorityName,
                init.submission_date as registrationDate,
                CONCAT(
                    init.applicant_name,
                    '\\nMobile: ', COALESCE(init.mobile_no, 'N/A'),
                    '\\nEmail: ', COALESCE(init.emails, 'N/A')
                ) as applicantDetails,
                init.name_of_centre as centreName,
                COALESCE(
                    'Advisory Committee: ' || exec.taskname || ' on ' || exec.executiontime,
                    'Pending Advisory Review'
                ) as advisorySummary,
                CASE
                    WHEN filt.status = 'Approve' THEN 'GRANTED - Order issued on ' || COALESCE(filt.executiontime, '')
                    WHEN filt.status = 'Reject' THEN 'REJECTED - Order issued on ' || COALESCE(filt.executiontime, '')
                    ELSE 'UNDER PROCESS'
                END as applicationOutcome,
                COALESCE(
                    -- Try multiple ways to extract registration number from JSON
                    CASE 
                        WHEN filt.officialform IS NOT NULL THEN 
                            COALESCE(
                                filt.officialform::json->>'143895',
                                'Not yet assigned'
                            )
                        ELSE 'Not yet assigned'
                    END,
                    'Not yet assigned'
                ) as registrationNumber,
                CASE
                    WHEN init.application_type LIKE '%Renewal%' THEN 'Renewal applied on: ' || init.submission_date
                    ELSE 'Initial Registration'
                END as renewalDetails,
                init.appl_ref_no as fileNumber,
                init.application_type as additionalInfo
            FROM pcpndt_initiated_data init
            LEFT JOIN (
                SELECT e1.application_id, e1.taskname, e1.status, e1.executiontime
                FROM pcpndt_execution_data e1
                INNER JOIN (
                    SELECT application_id, MAX(executiontime) as max_time
                    FROM pcpndt_execution_data
                    WHERE taskname LIKE '%Advisory%' OR taskname LIKE '%Committee%' OR taskname LIKE '%DAA%'
                    GROUP BY application_id
                ) e2 ON e1.application_id = e2.application_id AND e1.executiontime = e2.max_time
            ) exec ON init.appl_id = exec.application_id
            LEFT JOIN (
                SELECT f1.application_id, f1.status, f1.executiontime, f1.officialform
                FROM pcpndt_filtered_execution f1
                INNER JOIN (
                    SELECT application_id, MAX(executiontime) as max_time
                    FROM pcpndt_filtered_execution
                    GROUP BY application_id
                ) f2 ON f1.application_id = f2.application_id AND f1.executiontime = f2.max_time
            ) filt ON init.appl_id = filt.application_id
            WHERE init.appl_ref_no = ?
            """;

        try {
            logger.info("Executing main query for reference: {}", referenceNumber);

            List<FormHRecordDTO> results = jdbcTemplate.query(sql, new Object[]{referenceNumber}, (rs, rowNum) -> {
                // Debug each field as we process it
                System.out.println("=== PROCESSING ROW ===");
                try {
                    Long applicationId = rs.getLong("applicationId");
                    System.out.println("applicationId: " + applicationId);

                    String authorityName = rs.getString("authorityName");
                    System.out.println("authorityName: " + authorityName);

                    String registrationDate = rs.getString("registrationDate");
                    System.out.println("registrationDate: " + registrationDate);

                    return new FormHRecordDTO(
                            applicationId,
                            authorityName,
                            registrationDate,
                            rs.getString("applicantDetails"),
                            rs.getString("centreName"),
                            rs.getString("advisorySummary"),
                            rs.getString("applicationOutcome"),
                            rs.getString("registrationNumber"),
                            rs.getString("renewalDetails"),
                            rs.getString("fileNumber"),
                            rs.getString("additionalInfo")
                    );
                } catch (Exception e) {
                    System.out.println("Error processing row: " + e.getMessage());
                    e.printStackTrace();
                    return null;
                }
            });

            logger.info("Main query completed. Results found: {}", results.size());

            // Filter out null results and return first valid one
            List<FormHRecordDTO> validResults = results.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            return validResults.isEmpty() ? null : validResults.get(0);

        } catch (Exception e) {
            logger.error("Error in main query for reference {}: {}", referenceNumber, e.getMessage(), e);
            System.out.println("MAIN QUERY ERROR: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Database error while searching application", e);
        }
    }

    private void debugTableData(String referenceNumber) {
        try {
            System.out.println("=== DEBUGGING TABLE DATA FOR: " + referenceNumber + " ===");

            // Check if record exists in initiated_data
            String checkInitSql = "SELECT appl_id, appl_ref_no, applicant_name FROM pcpndt_initiated_data WHERE appl_ref_no = ?";
            jdbcTemplate.query(checkInitSql, new Object[]{referenceNumber}, (rs, rowNum) -> {
                System.out.println("INITIATED DATA - appl_id: " + rs.getString("appl_id") +
                        ", appl_ref_no: " + rs.getString("appl_ref_no") +
                        ", applicant_name: " + rs.getString("applicant_name"));
                return null;
            });

            // Check execution_data
            String checkExecSql = "SELECT application_id, taskname FROM pcpndt_execution_data WHERE application_id IN (SELECT appl_id FROM pcpndt_initiated_data WHERE appl_ref_no = ?)";
            jdbcTemplate.query(checkExecSql, new Object[]{referenceNumber}, (rs, rowNum) -> {
                System.out.println("EXECUTION DATA - application_id: " + rs.getString("application_id") +
                        ", taskname: " + rs.getString("taskname"));
                return null;
            });

            // Check filtered_execution
            String checkFiltSql = "SELECT application_id, status, officialform FROM pcpndt_filtered_execution WHERE application_id IN (SELECT appl_id FROM pcpndt_initiated_data WHERE appl_ref_no = ?)";
            jdbcTemplate.query(checkFiltSql, new Object[]{referenceNumber}, (rs, rowNum) -> {
                System.out.println("FILTERED EXECUTION - application_id: " + rs.getString("application_id") +
                        ", status: " + rs.getString("status") +
                        ", officialform: " + rs.getString("officialform"));
                return null;
            });

            System.out.println("=== END DEBUGGING ===");

        } catch (Exception e) {
            System.out.println("Debug query failed: " + e.getMessage());
        }
    }
}