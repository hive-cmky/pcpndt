package in.gov.serviceplus.pcpndt.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FormHDataDto {
    // 1. Sl. No
    private Integer serialNumber;

    // 2. File number of Appropriate Authority
    private String fileNumber;

    // 3. Date of receipt of application for grant of registration
    private String receiptDate;

    // 4. Name, Address, Phone/Fax etc. of Applicant
    private String applicantName;
    private String applicantAddress;
    private String phoneNumber;
    private String email;
    private String fax;
    private String applicantDetails;

    // 5. Name and address(es) of Genetic Counselling Centre / Genetic Laboratory /
    //    Genetic Clinic / Ultrasound Clinic / Imaging Centre
    private String facilityName;
    private String facilityAddress;
    private String facilityType;
    private String district;

    // 6. Date of consideration by Advisory Committee and recommendation of Advisory Committee, in summary
    private String advisoryCommitteeDate;
    private String recommendationSummary;
    private String advisorySummary;

    // 7. Outcome of application (state granted/rejected and date of issue of orders)
    private String outcome;
    private String orderDate;
    private String formType;

    // 8. Registration number allotted and date of expiry of registration
    private String registrationNumber;
    private String expiryDate;

    // 9. Renewals (date of renewal and renewed upto)
    private List<RenewalDto> renewals;
    private String renewalDetails;

    // 10. File number in which renewals dealt
    private String renewalFileNumber;

    // 11. Additional information, if any
    private String additionalInfo;

    // Constructors
    public FormHDataDto() {
        // Initialize with "NA" for required fields
        this.serialNumber = 1; // Simple counter starting from 1
        this.fileNumber = "NA";
        this.receiptDate = "NA";
        this.applicantName = "NA";
        this.applicantAddress = "NA";
        this.phoneNumber = "NA";
        this.email = "NA";
        this.fax = "NA";
        this.applicantDetails = "NA";
        this.facilityName = "NA";
        this.facilityAddress = "NA";
        this.facilityType = "NA";
        this.district = "NA";
        this.advisoryCommitteeDate = "NA";
        this.recommendationSummary = "NA";
        this.advisorySummary = "NA";
        this.outcome = "NA";
        this.orderDate = "NA";
        this.formType = "NA";
        this.registrationNumber = "NA";
        this.expiryDate = "NA";
        this.renewalDetails = "NA";
        this.renewalFileNumber = "NA";
        this.additionalInfo = "NA";
    }

    // Getters and Setters
    public Integer getSerialNumber() { return serialNumber; }
    public void setSerialNumber(Integer serialNumber) { this.serialNumber = serialNumber; }

    public String getFileNumber() { return fileNumber; }
    public void setFileNumber(String fileNumber) { this.fileNumber = fileNumber; }

    public String getReceiptDate() { return receiptDate; }
    public void setReceiptDate(String receiptDate) { this.receiptDate = receiptDate; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public String getApplicantAddress() { return applicantAddress; }
    public void setApplicantAddress(String applicantAddress) { this.applicantAddress = applicantAddress; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }

    public String getApplicantDetails() { return applicantDetails; }
    public void setApplicantDetails(String applicantDetails) { this.applicantDetails = applicantDetails; }

    public String getFacilityName() { return facilityName; }
    public void setFacilityName(String facilityName) { this.facilityName = facilityName; }

    public String getFacilityAddress() { return facilityAddress; }
    public void setFacilityAddress(String facilityAddress) { this.facilityAddress = facilityAddress; }

    public String getFacilityType() { return facilityType; }
    public void setFacilityType(String facilityType) { this.facilityType = facilityType; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getAdvisoryCommitteeDate() { return advisoryCommitteeDate; }
    public void setAdvisoryCommitteeDate(String advisoryCommitteeDate) { this.advisoryCommitteeDate = advisoryCommitteeDate; }

    public String getRecommendationSummary() { return recommendationSummary; }
    public void setRecommendationSummary(String recommendationSummary) { this.recommendationSummary = recommendationSummary; }

    public String getAdvisorySummary() { return advisorySummary; }
    public void setAdvisorySummary(String advisorySummary) { this.advisorySummary = advisorySummary; }

    public String getOutcome() { return outcome; }
    public void setOutcome(String outcome) { this.outcome = outcome; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public String getFormType() { return formType; }
    public void setFormType(String formType) { this.formType = formType; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public List<RenewalDto> getRenewals() { return renewals; }
    public void setRenewals(List<RenewalDto> renewals) { this.renewals = renewals; }

    public String getRenewalDetails() { return renewalDetails; }
    public void setRenewalDetails(String renewalDetails) { this.renewalDetails = renewalDetails; }

    public String getRenewalFileNumber() { return renewalFileNumber; }
    public void setRenewalFileNumber(String renewalFileNumber) { this.renewalFileNumber = renewalFileNumber; }

    public String getAdditionalInfo() { return additionalInfo; }
    public void setAdditionalInfo(String additionalInfo) { this.additionalInfo = additionalInfo; }
}
