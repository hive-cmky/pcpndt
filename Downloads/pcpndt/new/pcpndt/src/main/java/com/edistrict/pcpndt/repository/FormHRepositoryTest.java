//package com.edistrict.pcpndt.repository;
//
//
//import com.edistrict.pcpndt.dto.*;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.*;
//
//@Repository
//public class FormHRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public Optional<FormHDataDTO> findFormHDataByApplRefNo(String applRefNo) {
//        String queryStr = """
//            SELECT new com.edistrict.pcpndt.dto.FormHDataDTO(
//                ROW_NUMBER() OVER (ORDER BY ini.submission_date) as slNo,
//                ini.appl_ref_no,
//                CAST(SUBSTRING(ini.submission_date, 1, 10) AS date),
//                ini.applicant_name,
//                COALESCE(
//                    jsonb_extract_path_text(ini.initiated_data, 'application_form_attributes', '48673'),
//                    ini.ownership_address
//                ),
//                ini.mobile_no,
//                ini.emails,
//                NULL as fax,
//                ini.name_of_centre,
//                COALESCE(
//                    jsonb_extract_path_text(ini.initiated_data, 'application_form_attributes', '48678'),
//                    ini.routinglocationname
//                ),
//                ini.type_of_institution,
//                ini.routinglocationname,
//                NULL as advisoryCommitteeDate,
//                'Recommended for approval after physical verification' as recommendationSummary,
//                CASE
//                    WHEN ini.application_type LIKE '%Registered%' THEN 'GRANTED'
//                    WHEN ini.application_type LIKE '%Rejected%' THEN 'REJECTED'
//                    WHEN ini.application_type = 'Renewal' THEN 'GRANTED'
//                    ELSE 'PENDING'
//                END,
//                CASE
//                    WHEN ini.application_type LIKE '%Registered%' OR ini.application_type = 'Renewal' THEN
//                        CAST(SUBSTRING(ini.due_date, 1, 10) AS date)
//                    ELSE NULL
//                END,
//                CASE
//                    WHEN ini.application_type LIKE '%Registered%' OR ini.application_type = 'Renewal' THEN 'Form B'
//                    ELSE NULL
//                END,
//                COALESCE(
//                    jsonb_extract_path_text(filt.officialform::jsonb, '143895'),
//                    'Pending'
//                ) as registrationNumber,
//                CAST(SUBSTRING(ini.due_date, 1, 10) AS date) as expiryDate,
//                CAST(ini.submission_date AS timestamp),
//                ini.application_type,
//                ini.submission_location,
//                ini.type_of_ownership,
//                ini.ownership_name,
//                ini.appl_id
//            )
//            FROM pcpndt_initiated_data ini
//            LEFT JOIN pcpndt_filtered_execution filt ON ini.appl_id = filt.application_id
//                AND filt.taskname = 'Certificate Generation By DAA'
//            WHERE ini.appl_ref_no = :applRefNo
//            """;
//
//        TypedQuery<FormHDataDTO> query = entityManager.createQuery(queryStr, FormHDataDTO.class);
//        query.setParameter("applRefNo", applRefNo);
//
//        try {
//            FormHDataDTO result = query.getSingleResult();
//
//            // Add renewal data for renewal applications
//            if ("Renewal".equals(result.getApplicationType())) {
//                RenewalDTO renewal = new RenewalDTO();
//                renewal.setRenewalDate(result.getOrderDate());
//                renewal.setRenewedUpto(result.getExpiryDate());
//                renewal.setRenewalFileNumber(result.getFileNumber());
//                result.setRenewals(List.of(renewal));
//                result.setRenewalFileNumber(result.getFileNumber());
//            }
//
//            // Set additional info
//            result.setAdditionalInfo(buildAdditionalInfo(result));
//
//            return Optional.of(result);
//        } catch (Exception e) {
//            return Optional.empty();
//        }
//    }
//
//    private String buildAdditionalInfo(FormHDataDTO data) {
//        StringBuilder info = new StringBuilder();
//
//        if ("Renewal".equals(data.getApplicationType())) {
//            info.append("Previous registration applied for renewal. ");
//        }
//
//        if (data.getSubmissionLocation() != null) {
//            info.append("Submitted at: ").append(data.getSubmissionLocation()).append(". ");
//        }
//
//        if (data.getOwnershipType() != null) {
//            info.append("Ownership: ").append(data.getOwnershipType()).append(". ");
//        }
//
//        return info.toString().trim();
//    }
//
////    public List<String> findAllApplRefNos() {
////        String queryStr = "SELECT DISTINCT appl_ref_no FROM pcpndt_initiated_data ORDER BY appl_ref_no";
////        return entityManager.createQuery(queryStr, String.class).getResultList();
////    }
////    public Optional<FormHDataDTO> findFormHDataByApplRefNo(String applRefNo) {
////        try {
////            // First, get the basic application data
////            String baseQuery = """
////                SELECT ini.appl_id, ini.appl_ref_no, ini.submission_date, ini.submission_location,
////                       ini.due_date, ini.application_type, ini.applicant_name, ini.name_of_centre,
////                       ini.mobile_no, ini.emails, ini.type_of_institution, ini.type_of_ownership,
////                       ini.ownership_name, ini.ownership_address, ini.routinglocationname,
////                       ini.makemodel, ini.empdetails, ini.initiated_data,
////                       filt.officialform as official_form_data
////                FROM pcpndt_initiated_data ini
////                LEFT JOIN pcpndt_filtered_execution filt ON ini.appl_id = filt.application_id
////                    AND filt.taskname = 'Certificate Generation By DAA'
////                WHERE ini.appl_ref_no = :applRefNo
////                """;
////
////            List<Object[]> results = entityManager.createNativeQuery(baseQuery)
////                    .setParameter("applRefNo", applRefNo)
////                    .getResultList();
////
////            if (results.isEmpty()) {
////                return Optional.empty();
////            }
////
////            Object[] row = results.get(0);
////            FormHDataDTO formHData = mapRowToFormHDataDTO(row);
////
////            return Optional.of(formHData);
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            return Optional.empty();
////        }
////    }
//
//    private FormHDataDTO mapRowToFormHDataDTO(Object[] row) {
//        FormHDataDTO dto = new FormHDataDTO();
//
//        // Basic mapping
//        dto.setApplId((String) row[0]);
//        dto.setFileNumber((String) row[1]);
//        dto.setSubmissionDateTime(parseDateTime((String) row[2]));
//        dto.setSubmissionLocation((String) row[3]);
//        dto.setExpiryDate(parseDate((String) row[4]));
//        dto.setApplicationType((String) row[5]);
//        dto.setApplicantName((String) row[6]);
//        dto.setFacilityName((String) row[7]);
//        dto.setPhoneNumber((String) row[8]);
//        dto.setEmail((String) row[9]);
//        dto.setTypeOfInstitution((String) row[10]);
//        dto.setOwnershipType((String) row[11]);
//        dto.setOwnershipName((String) row[12]);
//        dto.setApplicantAddress((String) row[13]);
//        dto.setDistrict((String) row[14]);
//
//        // Extract machine details from makemodel JSON
//        String makemodelJson = (String) row[15];
//        dto.setMachineDetails(extractMachineDetails(makemodelJson));
//
//        // Extract employee details from empdetails JSON
//        String empdetailsJson = (String) row[16];
//        dto.setEmployeeDetails(extractEmployeeDetails(empdetailsJson));
//
//        // Extract additional data from initiated_data JSONB
//        String initiatedDataJson = (String) row[17];
//        extractFromInitiatedData(dto, initiatedDataJson);
//
//        // Extract from official form data
//        String officialFormJson = (String) row[18];
//        extractFromOfficialForm(dto, officialFormJson);
//
//        // Set derived fields
//        setDerivedFields(dto);
//
//        return dto;
//    }
//
//    private MachineDetailsDTO extractMachineDetails(String makemodelJson) {
//        if (makemodelJson == null || makemodelJson.trim().isEmpty()) {
//            return null;
//        }
//
//        try {
//            // Parse the JSON string to extract machine details
//            // Example: {"f1": 1, "49129_1": "WIPRO GE", "49130_1": "LOGIQ V5", "49131_1": "WIPROGE", "49132_1": "12/06/2018"}
//            Map<String, Object> machineMap = parseJsonToMap(makemodelJson);
//
//            MachineDetailsDTO machineDetails = new MachineDetailsDTO();
//            List<MachineItemDTO> machines = new ArrayList<>();
//            MachineItemDTO machine = new MachineItemDTO();
//
//            // Extract machine details from the JSON structure
//            machine.setMake(getStringValue(machineMap, "49129_1"));
//            machine.setModel(getStringValue(machineMap, "49130_1"));
//            machine.setManufacturer(getStringValue(machineMap, "49131_1"));
//            machine.setPurchaseDate(getStringValue(machineMap, "49132_1"));
//
//            machines.add(machine);
//            machineDetails.setMachines(machines);
//
//            // Set primary machine details
//            if (!machines.isEmpty()) {
//                machineDetails.setMake(machines.get(0).getMake());
//                machineDetails.setModel(machines.get(0).getModel());
//                machineDetails.setManufacturer(machines.get(0).getManufacturer());
//                machineDetails.setPurchaseDate(machines.get(0).getPurchaseDate());
//            }
//
//            return machineDetails;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private List<EmployeeDetailsDTO> extractEmployeeDetails(String empdetailsJson) {
//        if (empdetailsJson == null || empdetailsJson.trim().isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        try {
//            // Parse the JSON string to extract employee details
//            // Example: {"f1": 1, "49270_1": "DR SUJOG KUMAR NAYAK", "53480_1": "O AND G", "53481_1": "DOCTOR", "53510_1": "NA", "53511_1": "15086"}
//            Map<String, Object> empMap = parseJsonToMap(empdetailsJson);
//
//            List<EmployeeDetailsDTO> employees = new ArrayList<>();
//            Integer employeeCount = getIntegerValue(empMap, "f1");
//
//            if (employeeCount != null && employeeCount > 0) {
//                for (int i = 1; i <= employeeCount; i++) {
//                    EmployeeDetailsDTO employee = new EmployeeDetailsDTO();
//                    employee.setName(getStringValue(empMap, "49270_" + i));
//                    employee.setQualification(getStringValue(empMap, "53480_" + i));
//                    employee.setDesignation(getStringValue(empMap, "53481_" + i));
//                    employee.setExperience(getStringValue(empMap, "53510_" + i));
//                    employee.setRegistrationNumber(getStringValue(empMap, "53511_" + i));
//
//                    if (employee.getName() != null && !employee.getName().trim().isEmpty()) {
//                        employees.add(employee);
//                    }
//                }
//            }
//
//            return employees;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Collections.emptyList();
//        }
//    }
//
//    private void extractFromInitiatedData(FormHDataDTO dto, String initiatedDataJson) {
//        if (initiatedDataJson == null || initiatedDataJson.trim().isEmpty()) {
//            return;
//        }
//
//        try {
//            Map<String, Object> initiatedData = parseJsonToMap(initiatedDataJson);
//
//            // Extract from application_form_attributes
//            Map<String, Object> formAttributes = getNestedMap(initiatedData, "application_form_attributes");
//            if (formAttributes != null) {
//                // Extract facility address
//                String facilityAddress = getStringValue(formAttributes, "48678");
//                if (facilityAddress != null && !facilityAddress.trim().isEmpty()) {
//                    dto.setFacilityAddress(facilityAddress);
//                }
//
//                // Extract applicant address if not already set
//                if (dto.getApplicantAddress() == null) {
//                    String applicantAddress = getStringValue(formAttributes, "48673");
//                    dto.setApplicantAddress(applicantAddress);
//                }
//            }
//
//            // Store raw initiated data for reference
//            dto.setInitiatedData(initiatedData);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void extractFromOfficialForm(FormHDataDTO dto, String officialFormJson) {
//        if (officialFormJson == null || officialFormJson.trim().isEmpty()) {
//            return;
//        }
//
//        try {
//            Map<String, Object> officialForm = parseJsonToMap(officialFormJson);
//
//            // Extract registration number
//            String registrationNumber = getStringValue(officialForm, "143895");
//            if (registrationNumber != null && !registrationNumber.trim().isEmpty()) {
//                dto.setRegistrationNumber(registrationNumber);
//            }
//
//            // Extract expiry date from official form
//            String expiryDateStr = getStringValue(officialForm, "143878");
//            if (expiryDateStr != null && !expiryDateStr.trim().isEmpty()) {
//                dto.setExpiryDate(parseDate(expiryDateStr));
//            }
//
//            // Extract order date (use execution date from filtered execution)
//            dto.setOrderDate(dto.getExpiryDate()); // Default to expiry date
//
//            // Set form type based on application type
//            if (dto.getApplicationType() != null &&
//                    (dto.getApplicationType().contains("Form B") || dto.getApplicationType().contains("Registered"))) {
//                dto.setFormType("Form B");
//            } else if (dto.getApplicationType() != null && dto.getApplicationType().contains("Form C")) {
//                dto.setFormType("Form C");
//            }
//
//            // Store raw official form data for reference
//            dto.setOfficialFormData(officialForm);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void setDerivedFields(FormHDataDTO dto) {
//        // Set receipt date from submission date
//        if (dto.getSubmissionDateTime() != null) {
//            dto.setReceiptDate(dto.getSubmissionDateTime().toLocalDate());
//        }
//
//        // Set outcome based on application type
//        if (dto.getApplicationType() != null) {
//            if (dto.getApplicationType().contains("Registered") || dto.getApplicationType().contains("Renewal")) {
//                dto.setOutcome("GRANTED");
//            } else if (dto.getApplicationType().contains("Rejected")) {
//                dto.setOutcome("REJECTED");
//            } else {
//                dto.setOutcome("PENDING");
//            }
//        }
//
//        // Set advisory committee date (default to 15 days after submission)
//        if (dto.getReceiptDate() != null) {
//            dto.setAdvisoryCommitteeDate(dto.getReceiptDate().plusDays(15));
//        }
//
//        // Set recommendation summary
//        dto.setRecommendationSummary("Recommended for approval after physical verification and document scrutiny");
//
//        // Handle renewals
//        if ("Renewal".equals(dto.getApplicationType())) {
//            RenewalDTO renewal = new RenewalDTO();
//            renewal.setRenewalDate(dto.getOrderDate() != null ? dto.getOrderDate() : dto.getReceiptDate());
//            renewal.setRenewedUpto(dto.getExpiryDate());
//            renewal.setRenewalFileNumber(dto.getFileNumber());
//            dto.setRenewals(List.of(renewal));
//            dto.setRenewalFileNumber(dto.getFileNumber());
//        }
//
//        // Set additional information
//        dto.setAdditionalInfo(buildAdditionalInfo(dto));
//    }
//
////    private String buildAdditionalInfo(FormHDataDTO dto) {
////        StringBuilder info = new StringBuilder();
////
////        if ("Renewal".equals(dto.getApplicationType())) {
////            info.append("Application submitted for renewal of registration. ");
////        }
////
////        if (dto.getMachineDetails() != null && dto.getMachineDetails().getMachines() != null) {
////            info.append("Equipment: ").append(dto.getMachineDetails().getMake())
////                    .append(" ").append(dto.getMachineDetails().getModel()).append(". ");
////        }
////
////        if (dto.getEmployeeDetails() != null && !dto.getEmployeeDetails().isEmpty()) {
////            info.append("Staff: ").append(dto.getEmployeeDetails().size()).append(" medical professional(s). ");
////        }
////
////        if (dto.getOwnershipType() != null) {
////            info.append("Ownership Type: ").append(dto.getOwnershipType()).append(". ");
////        }
////
////        return info.toString().trim();
////    }
//
//    // Utility methods for JSON parsing
//    private Map<String, Object> parseJsonToMap(String jsonString) {
//        try {
//            // Remove the escaping if present
//            String cleanJson = jsonString.replace("\\\"", "\"");
//            // Simple JSON parsing (in production, use Jackson ObjectMapper)
//            // This is a simplified version - you might need proper JSON parsing
//            return Collections.emptyMap(); // Placeholder
//        } catch (Exception e) {
//            return Collections.emptyMap();
//        }
//    }
//
//    private String getStringValue(Map<String, Object> map, String key) {
//        return map.get(key) != null ? map.get(key).toString() : null;
//    }
//
//    private Integer getIntegerValue(Map<String, Object> map, String key) {
//        try {
//            return map.get(key) != null ? Integer.parseInt(map.get(key).toString()) : null;
//        } catch (NumberFormatException e) {
//            return null;
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private Map<String, Object> getNestedMap(Map<String, Object> parentMap, String key) {
//        Object value = parentMap.get(key);
//        return value instanceof Map ? (Map<String, Object>) value : null;
//    }
//
//    private LocalDateTime parseDateTime(String dateTimeStr) {
//        try {
//            // Parse date in format "dd-MM-yyyy HH:mm" or "dd-MM-yyyy HH:mm:ss"
//            if (dateTimeStr == null) return null;
//            String[] parts = dateTimeStr.split(" ");
//            return parseDate(parts[0]).atTime(0, 0); // Simplified parsing
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    private LocalDate parseDate(String dateStr) {
//        try {
//            if (dateStr == null) return null;
//            String[] parts = dateStr.split("-");
//            if (parts.length == 3) {
//                return LocalDate.of(
//                        Integer.parseInt(parts[2]),
//                        Integer.parseInt(parts[1]),
//                        Integer.parseInt(parts[0])
//                );
//            }
//            return null;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
////    public List<String> findAllApplRefNos() {
////        String queryStr = "SELECT DISTINCT appl_ref_no FROM pcpndt_initiated_data ORDER BY appl_ref_no";
////        return entityManager.createNativeQuery(queryStr, String.class).getResultList();
////    }
//
//}