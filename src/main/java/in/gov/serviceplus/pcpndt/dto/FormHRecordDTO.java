package in.gov.serviceplus.pcpndt.dto;

public class FormHRecordDTO {
//    private Long applicationId;
//    private String authorityName;
//    private String registrationDate;
//    private String applicantDetails;
//    private String centreName;
//    private String advisorySummary;
//    private String applicationOutcome;
//    private String registrationNumber;
//    private String renewalDetails;
//    private String fileNumber;
//    private String additionalInfo;
//
//    // default constructor
//    public FormHRecordDTO(){};
//
//    // parameterized constructor
//    public FormHRecordDTO(Long applicationId, String authorityName, String registrationDate, String applicantDetails,
//                          String centreName, String advisorySummary, String applicationOutcome,
//                          String registrationNumber, String renewalDetails, String fileNumber, String additionalInfo) {
//        this.applicationId = applicationId;
//        this.authorityName = authorityName;
//        this.registrationDate = registrationDate;
//        this.applicantDetails = applicantDetails;
//        this.centreName = centreName;
//        this.advisorySummary = advisorySummary;
//        this.applicationOutcome = applicationOutcome;
//        this.registrationNumber = registrationNumber;
//        this.renewalDetails = renewalDetails;
//        this.fileNumber = fileNumber;
//        this.additionalInfo = additionalInfo;
//    }

    private Long applicationId;

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    private String applicantName;
    private String authorityName;
    private String registrationDate;
    private String applicantDetails;
    private String centreName;
    private String advisorySummary;
    private String applicationOutcome;
    private String registrationNumber;
    private String renewalDetails;
    private String fileNumber;
    private String additionalInfo;

    // Add a constructor that handles null values
    public FormHRecordDTO(Long applicationId, String authorityName, String registrationDate,
                          String applicantDetails, String centreName, String advisorySummary,
                          String applicationOutcome, String registrationNumber, String renewalDetails,
                          String fileNumber, String additionalInfo) {
        this.applicationId = applicationId;
        this.authorityName = authorityName != null ? authorityName : "N/A";
        this.registrationDate = registrationDate != null ? registrationDate : "N/A";
        this.applicantDetails = applicantDetails != null ? applicantDetails : "N/A";
        this.centreName = centreName != null ? centreName : "N/A";
        this.advisorySummary = advisorySummary != null ? advisorySummary : "N/A";
        this.applicationOutcome = applicationOutcome != null ? applicationOutcome : "N/A";
        this.registrationNumber = registrationNumber != null ? registrationNumber : "N/A";
        this.renewalDetails = renewalDetails != null ? renewalDetails : "N/A";
        this.fileNumber = fileNumber != null ? fileNumber : "N/A";
        this.additionalInfo = additionalInfo != null ? additionalInfo : "N/A";
    }

    // getters and setters method

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getApplicantDetails() {
        return applicantDetails;
    }

    public void setApplicantDetails(String applicantDetails) {
        this.applicantDetails = applicantDetails;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getAdvisorySummary() {
        return advisorySummary;
    }

    public void setAdvisorySummary(String advisorySummary) {
        this.advisorySummary = advisorySummary;
    }

    public String getApplicationOutcome() {
        return applicationOutcome;
    }

    public void setApplicationOutcome(String applicationOutcome) {
        this.applicationOutcome = applicationOutcome;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRenewalDetails() {
        return renewalDetails;
    }

    public void setRenewalDetails(String renewalDetails) {
        this.renewalDetails = renewalDetails;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
