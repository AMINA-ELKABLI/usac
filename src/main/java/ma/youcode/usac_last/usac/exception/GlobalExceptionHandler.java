package ma.youcode.usac_last.usac.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex, "UNEXPECTED_ERROR", request);
    }
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public class TokenException extends RuntimeException {

        public TokenException(String token, String message) {
            super(String.format("Failed for [%s]: %s", token, message));
        }

    }



    @ExceptionHandler(ConcurrentUpdateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleConcurrentUpdateException(ConcurrentUpdateException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex, "CONCURRENT_UPDATE", request);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity notValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @ExceptionHandler(DatabaseAccessExceptio.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleDatabaseAccessException(DatabaseAccessExceptio ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex, "DATABASE_ACCESS_ERROR", request);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex, "RESOURCE_ALREADY_EXISTS", request);
    }


    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleInvalidDataException(InvalidDataException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex, "INVALID_DATA", request);
    }


    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, Exception ex, String errorCode, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(status).body(errorResponse);
    }
}
