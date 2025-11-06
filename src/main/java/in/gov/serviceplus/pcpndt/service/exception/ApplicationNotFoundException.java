package in.gov.serviceplus.pcpndt.service.exception;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException(String message) {
        super(message);
    }
    public ApplicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
