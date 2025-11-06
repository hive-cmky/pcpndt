//package in.gov.serviceplus.pcpndt.entity;
////
////import jakarta.persistence.Column;
////import jakarta.persistence.Entity;
////import jakarta.persistence.Id;
////import jakarta.persistence.Table;
////
////@Entity
////@Table(name="applications")
////public class Application {
////
////    @Id
////    @Column(name="appl_id")
////    private String applicationId;
////
////    @Column(name = "service_id")
////    private String serviceId;
////    @Column(name = "service_name")
////    private String serviceName;
////    @Column(name = "appl_ref_no")
////    private String applicationRefNo;
////    @Column(name = "submission_date")
////    private String submissionDate;
////    @Column(name = "submission_location")
////    private String submissionLocation;
////    @Column(name = "due_date")
////    private String dueDate;
////    @Column(name = "applicant_name")
////    private String applicantName;
////    @Column(name = "name_of_centre")
////    private String centerName;
////    @Column(name = "mobile_no")
////    private String mobileNo;
////    @Column(name = "emails")
////    private String email;
////    @Column(name = "application_type")
////    private String applicationType;
////    @Column(name = "routinglocationname")
////    private String district;
////
////    public String getApplicationId() {
////        return applicationId;
////    }
////
////    public void setApplicationId(String applicationId) {
////        this.applicationId = applicationId;
////    }
////
////    public String getServiceId() {
////        return serviceId;
////    }
////
////    public void setServiceId(String serviceId) {
////        this.serviceId = serviceId;
////    }
////
////    public String getServiceName() {
////        return serviceName;
////    }
////
////    public void setServiceName(String serviceName) {
////        this.serviceName = serviceName;
////    }
////
////    public String getApplicationRefNo() {
////        return applicationRefNo;
////    }
////
////    public void setApplicationRefNo(String applicationRefNo) {
////        this.applicationRefNo = applicationRefNo;
////    }
////
////    public String getSubmissionDate() {
////        return submissionDate;
////    }
////
////    public void setSubmissionDate(String submissionDate) {
////        this.submissionDate = submissionDate;
////    }
////
////    public String getSubmissionLocation() {
////        return submissionLocation;
////    }
////
////    public void setSubmissionLocation(String submissionLocation) {
////        this.submissionLocation = submissionLocation;
////    }
////
////    public String getDueDate() {
////        return dueDate;
////    }
////
////    public void setDueDate(String dueDate) {
////        this.dueDate = dueDate;
////    }
////
////    public String getApplicantName() {
////        return applicantName;
////    }
////
////    public void setApplicantName(String applicantName) {
////        this.applicantName = applicantName;
////    }
////
////    public String getCenterName() {
////        return centerName;
////    }
////
////    public void setCenterName(String centerName) {
////        this.centerName = centerName;
////    }
////
////    public String getMobileNo() {
////        return mobileNo;
////    }
////
////    public void setMobileNo(String mobileNo) {
////        this.mobileNo = mobileNo;
////    }
////
////    public String getEmail() {
////        return email;
////    }
////
////    public void setEmail(String email) {
////        this.email = email;
////    }
////
////    public String getApplicationType() {
////        return applicationType;
////    }
////
////    public void setApplicationType(String applicationType) {
////        this.applicationType = applicationType;
////    }
////
////    public String getDistrict() {
////        return district;
////    }
////
////    public void setDistrict(String district) {
////        this.district = district;
////    }
////}
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "application_data")
//public class Application {
//
//    @Id
//    @Column(name = "appl_id")
//    private Long applicationId;
//
//    @Column(name = "service_id")
//    private String serviceId;
//
//    @Column(name = "service_name")
//    private String serviceName;
//
//    @Column(name = "appl_ref_no")
//    private String applicationRefNo;
//
//    @Column(name = "submission_date")
//    private String submissionDate;
//
//    @Column(name = "submission_location")
//    private String submissionLocation;
//
//    @Column(name = "applicant_name")
//    private String applicantName;
//
//    @Column(name = "name_of_centre")
//    private String nameOfCentre;
//
//    @Column(name = "mobile_no")
//    private String mobileNo;
//
//    @Column(name = "emails")
//    private String email;
//
//    @Column(name = "type_of_institution")
//    private String institutionType;
//
//    @Column(name = "application_type")
//    private String applicationType;
//
//    @Column(columnDefinition = "jsonb")
//    private String initiated_data;
//
//    // Getters and Setters
//    public Long getApplicationId() { return applicationId; }
//    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }
//
//    public String getServiceId() { return serviceId; }
//    public void setServiceId(String serviceId) { this.serviceId = serviceId; }
//
//    public String getServiceName() { return serviceName; }
//    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
//
//    public String getApplicationRefNo() { return applicationRefNo; }
//    public void setApplicationRefNo(String applicationRefNo) { this.applicationRefNo = applicationRefNo; }
//
//    public String getSubmissionDate() { return submissionDate; }
//    public void setSubmissionDate(String submissionDate) { this.submissionDate = submissionDate; }
//
//    public String getSubmissionLocation() { return submissionLocation; }
//    public void setSubmissionLocation(String submissionLocation) { this.submissionLocation = submissionLocation; }
//
//    public String getApplicantName() { return applicantName; }
//    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }
//
//    public String getNameOfCentre() { return nameOfCentre; }
//    public void setNameOfCentre(String nameOfCentre) { this.nameOfCentre = nameOfCentre; }
//
//    public String getMobileNo() { return mobileNo; }
//    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }
//
//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }
//
//    public String getInstitutionType() { return institutionType; }
//    public void setInstitutionType(String institutionType) { this.institutionType = institutionType; }
//
//    public String getApplicationType() { return applicationType; }
//    public void setApplicationType(String applicationType) { this.applicationType = applicationType; }
//
//    public String getInitiatedData() { return initiated_data; }
//    public void setInitiatedData(String initiated_data) { this.initiated_data = initiated_data; }
//}
