package com.edistrict.pcpndt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;

@Data
public class RenewalDTO {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate renewalDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate renewedUpto;

    private String renewalFileNumber;
}

