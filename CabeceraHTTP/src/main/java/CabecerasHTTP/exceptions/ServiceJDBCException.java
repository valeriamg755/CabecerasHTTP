package CabecerasHTTP.exceptions;

public class ServiceJDBCException extends RuntimeException{
    public ServiceJDBCException(String message) {
        super(message);
    }
    public ServiceJDBCException(String message, Throwable cause) {
        super(message, cause);
    }
}