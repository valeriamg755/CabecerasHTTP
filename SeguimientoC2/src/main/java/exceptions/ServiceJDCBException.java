package exceptions;

public class ServiceJDCBException extends RuntimeException {
    public ServiceJDCBException(String message) {
        super(message);
    }
    public ServiceJDCBException(String message, Throwable cause) {
        super(message, cause);
    }
}
