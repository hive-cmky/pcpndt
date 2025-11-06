package com.edistrict.pcpndt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class FormHDataDTO {
    private Integer slNo;
    private String fileNumber;

    private String applRefNo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate receiptDate;

    private String applicantName;
    private String applicantAddress;
    private String phoneNumber;
    private String email;
    private String fax;

    private String facilityName;
    private String facilityAddress;
    private String facilityType;
    private String district;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate advisoryCommitteeDate;
    private String recommendationSummary;

    private String outcome;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate orderDate;
    private String formType;

    private String registrationNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expiryDate;

    private List<RenewalDTO> renewals;
    private String renewalFileNumber;
    private String additionalInfo;

    // Machine and Employee details from JSON
    private MachineDetailsDTO machineDetails;
    private List<EmployeeDetailsDTO> employeeDetails;

    // Additional fields from database
    private String applicationType;
    private String submissionLocation;
    private String typeOfInstitution;
    private String ownershipType;
    private String ownershipName;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime submissionDateTime;

    private String applId;

    // Raw JSON data for debugging
    private Map<String, Object> initiatedData;
    private Map<String, Object> officialFormData;


}
