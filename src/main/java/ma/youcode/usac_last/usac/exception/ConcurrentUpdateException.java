package ma.youcode.usac_last.usac.exception;

public class ConcurrentUpdateException extends RuntimeException{
    public ConcurrentUpdateException(String message) {
        super(message);
    }
}
