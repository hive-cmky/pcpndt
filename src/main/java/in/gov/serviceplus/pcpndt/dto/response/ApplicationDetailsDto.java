package in.gov.serviceplus.pcpndt.dto.response;

import in.gov.serviceplus.pcpndt.entity.ApplicationMaster;

public class ApplicationDetailsDto {
    private String applId;
    private String applRefNo;
    private String serviceName;
    private String applicantName;
    private String nameOfCentre;
    private String mobileNo;
    private String emails;
    private String submissionDate;
    private String applicationType;
    private String status;

    // Constructor from Entity
    public ApplicationDetailsDto(ApplicationMaster applicationMaster) {
        this.applId = applicationMaster.getApplId();
        this.applRefNo = applicationMaster.getApplRefNo();
        this.serviceName = applicationMaster.getServiceName();
        this.applicantName = applicationMaster.getApplicantName();
        this.nameOfCentre = applicationMaster.getNameOfCentre();
        this.mobileNo = applicationMaster.getMobileNo();
        this.emails = applicationMaster.getEmails();
        this.submissionDate = applicationMaster.getSubmissionDate();
        this.applicationType = applicationMaster.getApplicationType();
        // You can derive status from execution data or add it as a field in ApplicationMaster
    }

    // Getters and Setters
    public String getApplId() { return applId; }
    public void setApplId(String applId) { this.applId = applId; }

    public String getApplRefNo() { return applRefNo; }
    public void setApplRefNo(String applRefNo) { this.applRefNo = applRefNo; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public String getNameOfCentre() { return nameOfCentre; }
    public void setNameOfCentre(String nameOfCentre) { this.nameOfCentre = nameOfCentre; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getEmails() { return emails; }
    public void setEmails(String emails) { this.emails = emails; }

    public String getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(String submissionDate) { this.submissionDate = submissionDate; }

    public String getApplicationType() { return applicationType; }
    public void setApplicationType(String applicationType) { this.applicationType = applicationType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
