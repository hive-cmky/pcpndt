package in.gov.serviceplus.pcpndt.dto.response;

import in.gov.serviceplus.pcpndt.entity.ApplicationExecution;
import in.gov.serviceplus.pcpndt.entity.ApplicationFilteredExecution;
import in.gov.serviceplus.pcpndt.entity.ApplicationMaster;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationSearchResult {
    private ApplicationDetailsDto applicationDetails;
    private List<ExecutionHistoryDto> executionHistory;
    private List<FilteredExecutionDto> filteredExecutions;
    private SearchMetadata metadata;

    // Constructors
    public ApplicationSearchResult() {}

    public ApplicationSearchResult(ApplicationMaster applicationMaster,
                                   List<ApplicationExecution> executionHistory,
                                   List<ApplicationFilteredExecution> filteredExecutions) {
        this.applicationDetails = new ApplicationDetailsDto(applicationMaster);
        this.executionHistory = executionHistory.stream()
                .map(ExecutionHistoryDto::new)
                .collect(Collectors.toList());
        this.filteredExecutions = filteredExecutions.stream()
                .map(FilteredExecutionDto::new)
                .collect(Collectors.toList());
        this.metadata = new SearchMetadata(
                executionHistory.size() + filteredExecutions.size(),
                LocalDateTime.now() // This should work now
        );
    }

    // Getters and Setters
    public ApplicationDetailsDto getApplicationDetails() {
        return applicationDetails;
    }

    public void setApplicationDetails(ApplicationDetailsDto applicationDetails) {
        this.applicationDetails = applicationDetails;
    }

    public List<ExecutionHistoryDto> getExecutionHistory() {
        return executionHistory;
    }

    public void setExecutionHistory(List<ExecutionHistoryDto> executionHistory) {
        this.executionHistory = executionHistory;
    }

    public List<FilteredExecutionDto> getFilteredExecutions() {
        return filteredExecutions;
    }

    public void setFilteredExecutions(List<FilteredExecutionDto> filteredExecutions) {
        this.filteredExecutions = filteredExecutions;
    }

    public SearchMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(SearchMetadata metadata) {
        this.metadata = metadata;
    }
}
