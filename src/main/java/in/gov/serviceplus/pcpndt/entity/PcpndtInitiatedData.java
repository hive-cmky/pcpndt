package in.gov.serviceplus.pcpndt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pcpndt_initiated_data")
public class PcpndtInitiatedData {

    @Id
    @Column(name = "appl_id")
    private Long appl_id;

    @Column(name = "service_id")
    private String service_id;

    @Column(name = "service_name")
    private String service_name;

    @Column(name = "appl_ref_no")
    private String appl_ref_no;

    @Column(name = "submission_date")
    private String submission_date;

    @Column(name = "submission_location")
    private String submission_location;

    @Column(name = "applicant_name")
    private String applicant_name;

    @Column(name = "name_of_centre")
    private String name_of_centre;

    @Column(name = "mobile_no")
    private String mobile_no;

    @Column(name = "emails")
    private String emails;

    @Column(name = "type_of_institution")
    private String type_of_institution;

    @Column(name = "application_type")
    private String application_type;

    @Column(name = "initiated_data", columnDefinition = "jsonb")
    private String initiated_data;

    // Getters and Setters
    public Long getAppl_id() { return appl_id; }
    public void setAppl_id(Long appl_id) { this.appl_id = appl_id; }

    public String getService_id() { return service_id; }
    public void setService_id(String service_id) { this.service_id = service_id; }

    public String getService_name() { return service_name; }
    public void setService_name(String service_name) { this.service_name = service_name; }

    public String getAppl_ref_no() { return appl_ref_no; }
    public void setAppl_ref_no(String appl_ref_no) { this.appl_ref_no = appl_ref_no; }

    public String getSubmission_date() { return submission_date; }
    public void setSubmission_date(String submission_date) { this.submission_date = submission_date; }

    public String getSubmission_location() { return submission_location; }
    public void setSubmission_location(String submission_location) { this.submission_location = submission_location; }

    public String getApplicant_name() { return applicant_name; }
    public void setApplicant_name(String applicant_name) { this.applicant_name = applicant_name; }

    public String getName_of_centre() { return name_of_centre; }
    public void setName_of_centre(String name_of_centre) { this.name_of_centre = name_of_centre; }

    public String getMobile_no() { return mobile_no; }
    public void setMobile_no(String mobile_no) { this.mobile_no = mobile_no; }

    public String getEmails() { return emails; }
    public void setEmails(String emails) { this.emails = emails; }

    public String getType_of_institution() { return type_of_institution; }
    public void setType_of_institution(String type_of_institution) { this.type_of_institution = type_of_institution; }

    public String getApplication_type() { return application_type; }
    public void setApplication_type(String application_type) { this.application_type = application_type; }

    public String getInitiated_data() { return initiated_data; }
    public void setInitiated_data(String initiated_data) { this.initiated_data = initiated_data; }
}