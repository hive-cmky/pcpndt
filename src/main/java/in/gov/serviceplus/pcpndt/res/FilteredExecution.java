//package in.gov.serviceplus.pcpndt.entity;
//
//import jakarta.persistence.*;
//
////@Entity
////@Table(name = "filtered_execution")
////public class FilteredExecution {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    @Column(name = "application_id")
////    private String applicationId;
////
////    @Column(name = "receivedtime")
////    private String receivedTime;
////
////    @Column(name = "executiontime")
////    private String executionTime;
////
////    @Column(name = "locname")
////    private String locationName;
////
////    @Column(name = "username")
////    private String username;
////
////    @Column(name = "taskname")
////    private String taskName;
////
////    @Column(name = "serviceid")
////    private String serviceId;
////
////    @Column(name = "taskid")
////    private String taskId;
////
////    @Column(name = "status")
////    private String status;
////
////    public Long getId() {
////        return id;
////    }
////
////    public void setId(Long id) {
////        this.id = id;
////    }
////
////    public String getApplicationId() {
////        return applicationId;
////    }
////
////    public void setApplicationId(String applicationId) {
////        this.applicationId = applicationId;
////    }
////
////    public String getReceivedTime() {
////        return receivedTime;
////    }
////
////    public void setReceivedTime(String receivedTime) {
////        this.receivedTime = receivedTime;
////    }
////
////    public String getExecutionTime() {
////        return executionTime;
////    }
////
////    public void setExecutionTime(String executionTime) {
////        this.executionTime = executionTime;
////    }
////
////    public String getLocationName() {
////        return locationName;
////    }
////
////    public void setLocationName(String locationName) {
////        this.locationName = locationName;
////    }
////
////    public String getUsername() {
////        return username;
////    }
////
////    public void setUsername(String username) {
////        this.username = username;
////    }
////
////    public String getTaskName() {
////        return taskName;
////    }
////
////    public void setTaskName(String taskName) {
////        this.taskName = taskName;
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
////    public String getTaskId() {
////        return taskId;
////    }
////
////    public void setTaskId(String taskId) {
////        this.taskId = taskId;
////    }
////
////    public String getStatus() {
////        return status;
////    }
////
////    public void setStatus(String status) {
////        this.status = status;
////    }
////}
//
//@Entity
//@Table(name = "filtered_execution")
//public class FilteredExecution {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "application_id")
//    private Long applicationId;
//
//    @Column(name = "receivedtime")
//    private String receivedTime;
//
//    @Column(name = "executiontime")
//    private String executionTime;
//
//    @Column(name = "locname")
//    private String locationName;
//
//    @Column(name = "username")
//    private String userName;
//
//    @Column(name = "taskname")
//    private String taskName;
//
//    @Column(name = "status")
//    private String status;
//
//    @Column(name = "officialform", columnDefinition = "jsonb")
//    private String officialForm;
//
//    // Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public Long getApplicationId() { return applicationId; }
//    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }
//
//    public String getReceivedTime() { return receivedTime; }
//    public void setReceivedTime(String receivedTime) { this.receivedTime = receivedTime; }
//
//    public String getExecutionTime() { return executionTime; }
//    public void setExecutionTime(String executionTime) { this.executionTime = executionTime; }
//
//    public String getLocationName() { return locationName; }
//    public void setLocationName(String locationName) { this.locationName = locationName; }
//
//    public String getUserName() { return userName; }
//    public void setUserName(String userName) { this.userName = userName; }
//
//    public String getTaskName() { return taskName; }
//    public void setTaskName(String taskName) { this.taskName = taskName; }
//
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
//
//    public String getOfficialForm() { return officialForm; }
//    public void setOfficialForm(String officialForm) { this.officialForm = officialForm; }
//}
