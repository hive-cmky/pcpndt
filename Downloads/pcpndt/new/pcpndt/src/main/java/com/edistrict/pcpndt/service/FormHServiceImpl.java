package com.edistrict.pcpndt.service;

import com.edistrict.pcpndt.dto.FormHDataDTO;
import com.edistrict.pcpndt.repository.FormHRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormHServiceImpl implements FormHService {

    private final FormHRepository formHRepository;

    @Override
    public Optional<FormHDataDTO> getFormHDataByApplRefNo(String applRefNo) {
        log.info("=== FORM H SERVICE CALLED ===");
        log.info("Searching for application: {}", applRefNo);

        try {
            log.info("Calling repository method...");
            Optional<FormHDataDTO> result = formHRepository.findFormHDataByApplRefNo(applRefNo);

            if (result.isPresent()) {
                log.info("SUCCESS: Repository returned data for {}", applRefNo);
                FormHDataDTO data = result.get();
                log.info("Applicant Name: {}", data.getApplicantName());
                log.info("Facility Name: {}", data.getFacilityName());
                log.info("Phone: {}", data.getPhoneNumber());
                log.info("Email: {}", data.getEmail());
            } else {
                log.warn("NO DATA: Repository returned empty for {}", applRefNo);
            }
            return result;

        } catch (Exception e) {
            log.error("ERROR in service for {}: {}", applRefNo, e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<String> getAllApplicationRefNos() {
        log.info("📋 Fetching all application reference numbers from repository");
        List<String> refNos = formHRepository.findAllApplRefNos();
        log.info("📊 Found {} application references", refNos.size());
        if (!refNos.isEmpty()) {
            log.info("📝 Sample references: {}", refNos.subList(0, Math.min(5, refNos.size())));
        }
        return refNos;
    }

    @Override
    public boolean isValidApplRefNo(String applRefNo) {
        log.info("Validating application reference: {}", applRefNo);
        if (applRefNo == null || applRefNo.trim().isEmpty()) {
            log.warn("Invalid: Application reference is null or empty");
            return false;
        }
        boolean isValid = getAllApplicationRefNos().contains(applRefNo.trim());
        log.info("Validation result for {}: {}", applRefNo, isValid ? "VALID" : "INVALID");
        return isValid;
    }
}