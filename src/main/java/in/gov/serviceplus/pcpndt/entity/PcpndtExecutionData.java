package in.gov.serviceplus.pcpndt.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "pcpndt_execution_data")
public class PcpndtExecutionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_id")
    private Long application_id;

    @Column(name = "receivedtime")
    private String receivedtime;

    @Column(name = "executiontime")
    private String executiontime;

    @Column(name = "locname")
    private String locname;

    @Column(name = "username")
    private String username;

    @Column(name = "taskname")
    private String taskname;

    @Column(name = "status")
    private String status;

    @Column(name = "officialform", columnDefinition = "jsonb")
    private String officialform;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getApplication_id() { return application_id; }
    public void setApplication_id(Long application_id) { this.application_id = application_id; }

    public String getReceivedtime() { return receivedtime; }
    public void setReceivedtime(String receivedtime) { this.receivedtime = receivedtime; }

    public String getExecutiontime() { return executiontime; }
    public void setExecutiontime(String executiontime) { this.executiontime = executiontime; }

    public String getLocname() { return locname; }
    public void setLocname(String locname) { this.locname = locname; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getTaskname() { return taskname; }
    public void setTaskname(String taskname) { this.taskname = taskname; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getOfficialform() { return officialform; }
    public void setOfficialform(String officialform) { this.officialform = officialform; }
}