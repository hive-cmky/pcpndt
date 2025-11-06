package com.edistrict.pcpndt.service;

import com.edistrict.pcpndt.dto.FormHDataDTO;

import java.util.List;
import java.util.Optional;

public interface FormHService {
    Optional<FormHDataDTO> getFormHDataByApplRefNo(String applRefNo);
    List<String> getAllApplicationRefNos();
    boolean isValidApplRefNo(String applRefNo);
}
