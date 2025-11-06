package in.gov.serviceplus.pcpndt.dto.response;

import in.gov.serviceplus.pcpndt.entity.ApplicationExecution;
import in.gov.serviceplus.pcpndt.entity.ApplicationFilteredExecution;
import in.gov.serviceplus.pcpndt.entity.ApplicationMaster;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SearchMetadata {
    private int totalExecutionRecords;
    private LocalDateTime searchTimestamp;
    private String searchType;

    public SearchMetadata(int totalExecutionRecords, LocalDateTime searchTimestamp) {
        this.totalExecutionRecords = totalExecutionRecords;
        this.searchTimestamp = searchTimestamp;
        this.searchType = "APPL_REF_NO_SEARCH";
    }

    // Getters and Setters
    public int getTotalExecutionRecords() {
        return totalExecutionRecords;
    }

    public void setTotalExecutionRecords(int totalExecutionRecords) {
        this.totalExecutionRecords = totalExecutionRecords;
    }

    public LocalDateTime getSearchTimestamp() {
        return searchTimestamp;
    }

    public void setSearchTimestamp(LocalDateTime searchTimestamp) {
        this.searchTimestamp = searchTimestamp;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
