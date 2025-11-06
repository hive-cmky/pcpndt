package com.edistrict.pcpndt.dto;

import lombok.Data;
import java.util.List;

@Data
public class MachineDetailsDTO {
    private String make;
    private String model;
    private String manufacturer;
    private String purchaseDate;
    private List<MachineItemDTO> machines;
}
