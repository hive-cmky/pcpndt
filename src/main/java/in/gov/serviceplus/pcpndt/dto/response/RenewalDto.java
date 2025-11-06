package in.gov.serviceplus.pcpndt.dto.response;


import java.time.LocalDate;


public class RenewalDto {
    private LocalDate renewalDate;
    private LocalDate renewedUpto;

    // Constructors
    public RenewalDto() {}

    public RenewalDto(LocalDate renewalDate, LocalDate renewedUpto) {
        this.renewalDate = renewalDate;
        this.renewedUpto = renewedUpto;
    }

    // Getters and Setters
    public LocalDate getRenewalDate() { return renewalDate; }
    public void setRenewalDate(LocalDate renewalDate) { this.renewalDate = renewalDate; }

    public LocalDate getRenewedUpto() { return renewedUpto; }
    public void setRenewedUpto(LocalDate renewedUpto) { this.renewedUpto = renewedUpto; }
}
