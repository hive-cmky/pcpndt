package com.edistrict.pcpndt.service;

import com.edistrict.pcpndt.entity.PcpndtInitiatedData;
import com.edistrict.pcpndt.repository.PcpndtInitiatedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PcpndtInitiatedDataService {

    @Autowired
    private PcpndtInitiatedDataRepository pcpndtInitiatedDataRepository;

    // Basic CRUD operations
    public Optional<PcpndtInitiatedData> findByApplRefNo(String applRefNo) {
        return pcpndtInitiatedDataRepository.findByApplRefNo(applRefNo);
    }

    public List<PcpndtInitiatedData> getAllApplications() {
        return pcpndtInitiatedDataRepository.findAllOrderBySubmissionDateDesc();
    }

    // Search methods
    public List<PcpndtInitiatedData> searchByApplRefNo(String applRefNo) {
        return pcpndtInitiatedDataRepository.searchByApplRefNo(applRefNo);
    }

    public List<PcpndtInitiatedData> findByApplicantNameContaining(String applicantName) {
        return pcpndtInitiatedDataRepository.findByApplicantNameContainingIgnoreCase(applicantName);
    }

    public List<PcpndtInitiatedData> findByNameOfCentreContaining(String nameOfCentre) {
        return pcpndtInitiatedDataRepository.findByNameOfCentreContainingIgnoreCase(nameOfCentre);
    }

    public List<PcpndtInitiatedData> findBySubmissionLocationContaining(String submissionLocation) {
        return pcpndtInitiatedDataRepository.findBySubmissionLocationContainingIgnoreCase(submissionLocation);
    }

    public List<PcpndtInitiatedData> findByApplicationType(String applicationType) {
        return pcpndtInitiatedDataRepository.findByApplicationType(applicationType);
    }

    // Advanced search methods
    public List<PcpndtInitiatedData> searchByApplicantNameOrCentreName(String searchTerm) {
        return pcpndtInitiatedDataRepository.searchByApplicantNameOrCentreName(searchTerm);
    }

    public List<PcpndtInitiatedData> comprehensiveSearch(String searchTerm) {
        return pcpndtInitiatedDataRepository.comprehensiveSearch(searchTerm);
    }

    // Utility methods
    public boolean existsByApplRefNo(String applRefNo) {
        return pcpndtInitiatedDataRepository.findByApplRefNo(applRefNo).isPresent();
    }

    public long getTotalApplications() {
        return pcpndtInitiatedDataRepository.count();
    }
}
