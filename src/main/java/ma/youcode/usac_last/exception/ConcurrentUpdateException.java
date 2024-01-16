package ma.youcode.usac_last.exception;

public class ConcurrentUpdateException extends RuntimeException{
    public ConcurrentUpdateException(String message) {
        super(message);
    }
}
