package com.edistrict.pcpndt.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pcpndt_initiated_data")
public class PcpndtInitiatedData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "appl_id")
    private String applId;

    @Column(name = "appl_ref_no")
    private String applRefNo;

    @Column(name = "submission_date")
    private String submissionDate;

    @Column(name = "submission_location")
    private String submissionLocation;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "payment_reference_no")
    private String paymentReferenceNo;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "payment_amount")
    private String paymentAmount;

    @Column(name = "base_service_id")
    private Long baseServiceId;

    @Column(name = "application_type")
    private String applicationType;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "name_of_centre")
    private String nameOfCentre;

    @Column(name = "routinglocation")
    private String routingLocation;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "emails")
    private String email;

    @Column(name = "type_of_institution")
    private String typeOfInstitution;

    @Column(name = "type_of_ownership")
    private String typeOfOwnership;

    @Column(name = "ownership_name")
    private String ownershipName;

    @Column(name = "ownership_address")
    private String ownershipAddress;

    @Column(columnDefinition = "jsonb")
    private String makemodel;

    @Column(columnDefinition = "jsonb")
    private String empdetails;

    @Column(columnDefinition = "jsonb", name = "initiated_data")
    private String initiatedData;

    @Column(name = "routinglocationid")
    private String routingLocationId;

    @Column(name = "routinglocationname")
    private String routingLocationName;

    // Constructors
    public PcpndtInitiatedData() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getServiceId() { return serviceId; }
    public void setServiceId(Long serviceId) { this.serviceId = serviceId; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getApplId() { return applId; }
    public void setApplId(String applId) { this.applId = applId; }

    public String getApplRefNo() { return applRefNo; }
    public void setApplRefNo(String applRefNo) { this.applRefNo = applRefNo; }

    public String getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(String submissionDate) { this.submissionDate = submissionDate; }

    public String getSubmissionLocation() { return submissionLocation; }
    public void setSubmissionLocation(String submissionLocation) { this.submissionLocation = submissionLocation; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getPaymentReferenceNo() { return paymentReferenceNo; }
    public void setPaymentReferenceNo(String paymentReferenceNo) { this.paymentReferenceNo = paymentReferenceNo; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(String paymentAmount) { this.paymentAmount = paymentAmount; }

    public Long getBaseServiceId() { return baseServiceId; }
    public void setBaseServiceId(Long baseServiceId) { this.baseServiceId = baseServiceId; }

    public String getApplicationType() { return applicationType; }
    public void setApplicationType(String applicationType) { this.applicationType = applicationType; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public String getNameOfCentre() { return nameOfCentre; }
    public void setNameOfCentre(String nameOfCentre) { this.nameOfCentre = nameOfCentre; }

    public String getRoutingLocation() { return routingLocation; }
    public void setRoutingLocation(String routingLocation) { this.routingLocation = routingLocation; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTypeOfInstitution() { return typeOfInstitution; }
    public void setTypeOfInstitution(String typeOfInstitution) { this.typeOfInstitution = typeOfInstitution; }

    public String getTypeOfOwnership() { return typeOfOwnership; }
    public void setTypeOfOwnership(String typeOfOwnership) { this.typeOfOwnership = typeOfOwnership; }

    public String getOwnershipName() { return ownershipName; }
    public void setOwnershipName(String ownershipName) { this.ownershipName = ownershipName; }

    public String getOwnershipAddress() { return ownershipAddress; }
    public void setOwnershipAddress(String ownershipAddress) { this.ownershipAddress = ownershipAddress; }

    public String getMakemodel() { return makemodel; }
    public void setMakemodel(String makemodel) { this.makemodel = makemodel; }

    public String getEmpdetails() { return empdetails; }
    public void setEmpdetails(String empdetails) { this.empdetails = empdetails; }

    public String getInitiatedData() { return initiatedData; }
    public void setInitiatedData(String initiatedData) { this.initiatedData = initiatedData; }

    public String getRoutingLocationId() { return routingLocationId; }
    public void setRoutingLocationId(String routingLocationId) { this.routingLocationId = routingLocationId; }

    public String getRoutingLocationName() { return routingLocationName; }
    public void setRoutingLocationName(String routingLocationName) { this.routingLocationName = routingLocationName; }
}
