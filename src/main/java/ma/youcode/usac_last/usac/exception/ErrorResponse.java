package ma.youcode.usac_last.usac.exception;

public record ErrorResponse(
        String timeStamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
