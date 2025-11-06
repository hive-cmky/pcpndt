//package com.edistrict.pcpndt.service;
//
//
//import com.edistrict.pcpndt.dto.FormHDataDTO;
//import com.edistrict.pcpndt.repository.FormHRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class FormHServiceImpl implements FormHService{
//    private final FormHRepository formHRepository;
//
//    @Override
//    public Optional<FormHDataDTO> getFormHDataByApplRefNo(String applRefNo) {
//        log.info("Fetching Form H data for application reference: {}", applRefNo);
//        try {
//            Optional<FormHDataDTO> result = formHRepository.findFormHDataByApplRefNo(applRefNo);
//            if (result.isPresent()) {
//                log.info("Successfully retrieved Form H data for: {}", applRefNo);
//            } else {
//                log.warn("No Form H data found for: {}", applRefNo);
//            }
//            return result;
//        } catch (Exception e) {
//            log.error("Error fetching Form H data for {}: {}", applRefNo, e.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public List<String> getAllApplicationRefNos() {
//        log.info("Fetching all application reference numbers");
//        return formHRepository.findAllApplRefNos();
//    }
//
//    @Override
//    public boolean isValidApplRefNo(String applRefNo) {
//        if (applRefNo == null || applRefNo.trim().isEmpty()) {
//            return false;
//        }
//        return getAllApplicationRefNos().contains(applRefNo.trim());
//    }
//}
