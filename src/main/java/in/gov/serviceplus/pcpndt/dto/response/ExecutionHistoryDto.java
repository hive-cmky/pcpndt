package in.gov.serviceplus.pcpndt.dto.response;

import in.gov.serviceplus.pcpndt.entity.ApplicationExecution;

public class ExecutionHistoryDto {
    private Long executionId;
    private String taskName;
    private String status;
    private String userName;
    private String locationName;
    private String receivedTime;
    private String executionTime;

    // Constructor from Entity
    public ExecutionHistoryDto(ApplicationExecution execution) {
        this.executionId = execution.getId();
        this.taskName = execution.getTaskName();
        this.status = execution.getStatus();
        this.userName = execution.getUserName();
        this.locationName = execution.getLocationName();
        this.receivedTime = execution.getReceivedTime();
        this.executionTime = execution.getExecutionTime();
    }

    // Getters and Setters
    public Long getExecutionId() { return executionId; }
    public void setExecutionId(Long executionId) { this.executionId = executionId; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }

    public String getReceivedTime() { return receivedTime; }
    public void setReceivedTime(String receivedTime) { this.receivedTime = receivedTime; }

    public String getExecutionTime() { return executionTime; }
    public void setExecutionTime(String executionTime) { this.executionTime = executionTime; }
}
