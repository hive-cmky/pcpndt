package in.gov.serviceplus.pcpndt.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.gov.serviceplus.pcpndt.dto.response.FormHDataDto;
import in.gov.serviceplus.pcpndt.entity.ApplicationExecution;
import in.gov.serviceplus.pcpndt.entity.ApplicationMaster;
import in.gov.serviceplus.pcpndt.repository.ApplicationExecutionRepository;
import in.gov.serviceplus.pcpndt.repository.ApplicationMasterRepository;
import in.gov.serviceplus.pcpndt.service.exception.ApplicationNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FormHDataService {
    private final ApplicationMasterRepository applicationMasterRepository;
    private final ApplicationExecutionRepository applicationExecutionRepository;
    private final ObjectMapper objectMapper;

    // Field mappings from JSON structures
    private static final String APPLICANT_NAME_FIELD = "48664";
    private static final String APPLICANT_ADDRESS_FIELD = "48673";
    private static final String PHONE_NUMBER_FIELD = "48680";
    private static final String EMAIL_FIELD = "48681";
    private static final String FACILITY_NAME_FIELD = "48676";
    private static final String FACILITY_ADDRESS_FIELD = "48678";
    private static final String FACILITY_TYPE_FIELD = "48674";
    private static final String DISTRICT_FIELD = "48675";

    // Official form field mappings
    private static final String OUTCOME_FIELD = "136514";
    private static final String EXPIRY_DATE_FIELD = "143878";
    private static final String REGISTRATION_NUMBER_FIELD = "143895";
    private static final String REMARKS_FIELD = "136516";

    public FormHDataService(ApplicationMasterRepository applicationMasterRepository,
                            ApplicationExecutionRepository applicationExecutionRepository) {
        this.applicationMasterRepository = applicationMasterRepository;
        this.applicationExecutionRepository = applicationExecutionRepository;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Get Form H data for a given application reference number
     */
    public FormHDataDto getFormHData(String applRefNo) {
        // Step 1: Find application master data
        ApplicationMaster applicationMaster = applicationMasterRepository.findByApplRefNo(applRefNo)
                .orElseThrow(() -> new ApplicationNotFoundException(
                        "Application not found with reference number: " + applRefNo));

        // Step 2: Get application ID to search execution data
        String applicationId = applicationMaster.getApplId();

        // Step 3: Create Form H DTO and populate basic data
        FormHDataDto formHData = new FormHDataDto();

        // Step 4: Populate basic data from application_master
        populateBasicData(formHData, applicationMaster);

        // Step 5: Extract and populate data from initiated_data JSON
        populateFromInitiatedData(formHData, applicationMaster.getInitiatedData());

        // Step 6: Extract and populate data from execution officialform JSON
        populateFromOfficialForm(formHData, applicationId);

        return formHData;
    }

    /**
     * Populate basic data from ApplicationMaster entity
     */
    private void populateBasicData(FormHDataDto formHData, ApplicationMaster applicationMaster) {
        // 1. Serial Number - simple increment (you can enhance this later)
        formHData.setSerialNumber(1);

        // 2. File number (appl_ref_no)
        if (applicationMaster.getApplRefNo() != null) {
            formHData.setFileNumber(applicationMaster.getApplRefNo());
        }

        // 3. Date of receipt (submission_date)
        if (applicationMaster.getSubmissionDate() != null) {
            formHData.setReceiptDate(applicationMaster.getSubmissionDate());
        }
    }

    /**
     * Extract and populate data from initiated_data JSON column
     */
    private void populateFromInitiatedData(FormHDataDto formHData, String initiatedDataJson) {
        if (initiatedDataJson == null || initiatedDataJson.trim().isEmpty()) {
            return;
        }

        try {
            JsonNode rootNode = objectMapper.readTree(initiatedDataJson);
            JsonNode applInfo = rootNode.path("appl_info");
            JsonNode formAttributes = rootNode.path("application_form_attributes");

            // Extract from application_form_attributes
            if (formAttributes.isObject()) {
                // 4. Applicant Details
                if (formAttributes.has(APPLICANT_NAME_FIELD)) {
                    formHData.setApplicantName(formAttributes.get(APPLICANT_NAME_FIELD).asText());
                }
                if (formAttributes.has(APPLICANT_ADDRESS_FIELD)) {
                    formHData.setApplicantAddress(formAttributes.get(APPLICANT_ADDRESS_FIELD).asText());
                }
                if (formAttributes.has(PHONE_NUMBER_FIELD)) {
                    formHData.setPhoneNumber(formAttributes.get(PHONE_NUMBER_FIELD).asText());
                }
                if (formAttributes.has(EMAIL_FIELD)) {
                    formHData.setEmail(formAttributes.get(EMAIL_FIELD).asText());
                }

                // 5. Facility Details
                if (formAttributes.has(FACILITY_NAME_FIELD)) {
                    formHData.setFacilityName(formAttributes.get(FACILITY_NAME_FIELD).asText());
                }
                if (formAttributes.has(FACILITY_ADDRESS_FIELD)) {
                    formHData.setFacilityAddress(formAttributes.get(FACILITY_ADDRESS_FIELD).asText());
                }
                if (formAttributes.has(FACILITY_TYPE_FIELD)) {
                    String facilityType = formAttributes.get(FACILITY_TYPE_FIELD).asText();
                    formHData.setFacilityType(extractValueAfterTilde(facilityType));
                }
                if (formAttributes.has(DISTRICT_FIELD)) {
                    String district = formAttributes.get(DISTRICT_FIELD).asText();
                    formHData.setDistrict(extractValueAfterTilde(district));
                }
            }

            // Additional applicant details from appl_info
            if (applInfo.isObject()) {
                if (applInfo.has("user_name")) {
                    formHData.setApplicantDetails("Applied by: " + applInfo.get("user_name").asText());
                }
            }

        } catch (Exception e) {
            // Log error but don't throw - we want to return whatever data we can extract
            System.err.println("Error parsing initiated_data JSON: " + e.getMessage());
        }
    }

    /**
     * Extract and populate data from officialform JSON in execution data
     */
    private void populateFromOfficialForm(FormHDataDto formHData, String applicationId) {
        // Find execution records for this application
        List<ApplicationExecution> executions = applicationExecutionRepository.findByApplicationId(applicationId);

        if (executions.isEmpty()) {
            return;
        }

        // Look for Certificate Generation task and extract officialform data
        for (ApplicationExecution execution : executions) {
            if (execution.getOfficialForm() != null && !execution.getOfficialForm().trim().isEmpty()) {
                try {
                    JsonNode officialForm = objectMapper.readTree(execution.getOfficialForm());

                    // 7. Outcome of application
                    if (officialForm.has(OUTCOME_FIELD)) {
                        String outcomeValue = officialForm.get(OUTCOME_FIELD).asText();
                        formHData.setOutcome(extractOutcomeStatus(outcomeValue));
                        formHData.setFormType(extractFormType(outcomeValue));
                    }

                    // 8. Registration number and expiry date
                    if (officialForm.has(REGISTRATION_NUMBER_FIELD)) {
                        String regNumber = officialForm.get(REGISTRATION_NUMBER_FIELD).asText();
                        if (!regNumber.trim().isEmpty()) {
                            formHData.setRegistrationNumber(regNumber);
                        }
                    }

                    if (officialForm.has(EXPIRY_DATE_FIELD)) {
                        String expiryDate = officialForm.get(EXPIRY_DATE_FIELD).asText();
                        if (!expiryDate.trim().isEmpty()) {
                            formHData.setExpiryDate(expiryDate);
                        }
                    }

                    // Order date from execution record
                    if (execution.getExecutionTime() != null) {
                        formHData.setOrderDate(execution.getExecutionTime());
                    }

                    // Additional info from remarks
                    if (officialForm.has(REMARKS_FIELD)) {
                        String remarks = officialForm.get(REMARKS_FIELD).asText();
                        if (!remarks.trim().isEmpty()) {
                            formHData.setAdditionalInfo(remarks);
                        }
                    }

                    // If we found relevant data, break out of loop
                    if (formHData.getOutcome() != null && !"NA".equals(formHData.getOutcome())) {
                        break;
                    }

                } catch (Exception e) {
                    // Log error but continue processing
                    System.err.println("Error parsing officialform JSON: " + e.getMessage());
                }
            }
        }

        // If no outcome found in officialform, check task status
        if ("NA".equals(formHData.getOutcome())) {
            determineOutcomeFromTasks(formHData, executions);
        }
    }

    /**
     * Determine outcome from task names and statuses
     */
    private void determineOutcomeFromTasks(FormHDataDto formHData, List<ApplicationExecution> executions) {
        for (ApplicationExecution execution : executions) {
            String taskName = execution.getTaskName();
            String status = execution.getStatus();

            if ("Certificate Generation By DAA".equals(taskName)) {
                if ("Approve".equalsIgnoreCase(status)) {
                    formHData.setOutcome("Granted");
                    formHData.setFormType("Form B");
                } else if ("Reject".equalsIgnoreCase(status)) {
                    formHData.setOutcome("Rejected");
                    formHData.setFormType("Form C");
                }

                if (execution.getExecutionTime() != null) {
                    formHData.setOrderDate(execution.getExecutionTime());
                }
                break;
            }
        }
    }

    /**
     * Extract value after tilde (e.g., "4~Ultrasound Clinic" → "Ultrasound Clinic")
     */
    private String extractValueAfterTilde(String value) {
        if (value == null) return "NA";
        int tildeIndex = value.indexOf('~');
        if (tildeIndex != -1 && tildeIndex + 1 < value.length()) {
            return value.substring(tildeIndex + 1).trim();
        }
        return value;
    }

    /**
     * Extract outcome status from officialform value (e.g., "10~Reject" → "Rejected")
     */
    private String extractOutcomeStatus(String outcomeValue) {
        if (outcomeValue == null) return "NA";

        String status = extractValueAfterTilde(outcomeValue);
        if ("Reject".equalsIgnoreCase(status)) {
            return "Rejected";
        } else if ("Approve".equalsIgnoreCase(status)) {
            return "Granted";
        }
        return status;
    }

    /**
     * Extract form type from outcome value
     */
    private String extractFormType(String outcomeValue) {
        if (outcomeValue == null) return "NA";

        String status = extractValueAfterTilde(outcomeValue);
        if ("Reject".equalsIgnoreCase(status)) {
            return "Form C";
        } else if ("Approve".equalsIgnoreCase(status)) {
            return "Form B";
        }
        return "NA";
    }

    /**
     * Get Form H data with custom serial number
     */
    public FormHDataDto getFormHData(String applRefNo, Integer serialNumber) {
        FormHDataDto formHData = getFormHData(applRefNo);
        formHData.setSerialNumber(serialNumber);
        return formHData;
    }
}
